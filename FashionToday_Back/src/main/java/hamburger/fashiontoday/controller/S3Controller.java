package hamburger.fashiontoday.controller;


import hamburger.fashiontoday.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @프로그램ID : HAM-PB-1003-J
 * @프로그램명 : S3Controller.java
 * @author : 심기성
 * @date : 2019.09.25
 * @version : 0.5
 *
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/image")
public class S3Controller {

    private final S3Uploader s3Uploader;

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("data") MultipartFile multipartFile) throws IOException {
      // return s3Uploader.upload(multipartFile, "lookitems");
        return "";
    }

}
