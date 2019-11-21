package hamburger.fashiontoday.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

/**
 * @author : 심기성
 * @version : 0.5
 * @프로그램ID : HAM-PB-4002-J
 * @프로그램명 : S3Uploader.java
 * @date : 2019.09.18
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class S3Uploader {

    private String programId = "HAM-PB-4002-J";
    private String errorCode = "";

    private final AmazonS3Client amazonS3Client = new AmazonS3Client();

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    /**
     * 멀티파트 파일 이름 바꿔 업로드 실행
     *
     * @param multipartParamFile
     * @param s3DirParamName     : S3 저장소 Path
     * @return
     * @throws IOException
     */
    public String upload(MultipartFile multipartParamFile, String s3DirParamName,long count) throws Exception {
        System.out.println(programId + " : upload1 : s3DirParamName = " + s3DirParamName);
        File uploadFile = convert(multipartParamFile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));
        File renameFile = new File(String.valueOf(count)+uploadFile.getName());
        uploadFile.renameTo(renameFile);
        return upload(uploadFile, s3DirParamName);
    }

    /**
     * 멀티파트 파일 업로드 실행
     *
     * @param multipartParamFile
     * @param s3DirParamName     : S3 저장소 Path
     * @return
     * @throws IOException
     */
    public String upload(MultipartFile multipartParamFile, String s3DirParamName) throws Exception {
        System.out.println(programId + " : upload1 : s3DirParamName = " + s3DirParamName);
        File uploadFile = convert(multipartParamFile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));
        return upload(uploadFile, s3DirParamName);
    }

    /**
     * 일반 파일 업로드 실행
     *
     * @param uploadParamFile
     * @param s3DirParamName
     * @return
     */
    private String upload(File uploadParamFile, String s3DirParamName){

        // S3에 올려질 임시파일 명칭
        String fileTmpName = new String();

        // S3에 올려지고 난 이후 이미지 url 경로
        String uploadImageUrl = new String();

        // S3에 올린 이미지 주소 수정
        String fixImageUrl;

        try {

            // S3에 올릴 파일 이름 가져오기
            fileTmpName = s3DirParamName + "/" + uploadParamFile.getName();
            System.out.println();
            // 업로드 및 업로드 이미지 불러오기
            uploadImageUrl = putS3(uploadParamFile, fileTmpName);

        } catch (Exception e) {
            e.printStackTrace();
            errorCode = "ERR-S100";
            System.out.println(programId + " Error code = " + errorCode);

        } finally {

            // ImageUrl경로가 잡힌 이후에 임시 파일을 삭제함
            if (uploadImageUrl != null && uploadImageUrl.length() > 0) {
                System.out.println(programId + " : upload2 - success : filename = " + fileTmpName + " uploadImageUrl = " + uploadImageUrl);
                removeNewFile(uploadParamFile);
            } else {
                System.out.println(programId + " : upload2 - fail : filename = " + fileTmpName + " uploadImageUrl = " + uploadImageUrl);
            }
        }

        fixImageUrl = "https://data.pashiontoday.com/"+uploadImageUrl.substring(47);
        return fixImageUrl;

    }

    /**
     * S3에 객체 올리기
     *
     * @param uploadParamFile
     * @param s3FileParamName
     * @return
     */
    private String putS3(File uploadParamFile, String s3FileParamName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, s3FileParamName, uploadParamFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return amazonS3Client.getUrl(bucket, s3FileParamName).toString();
    }

    /**
     * 임시파일 삭제
     *
     * @param targetFile
     */
    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }

    /**
     * multipartfile을 file로 변환
     *
     * @param paramFile
     * @return
     * @throws IOException
     */
    private Optional<File> convert(MultipartFile paramFile) throws IOException {
        File convertFile = new File(paramFile.getOriginalFilename());
        if (convertFile.createNewFile())
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(paramFile.getBytes());
            }
        return Optional.of(convertFile);

    }
}