package hamburger.fashiontoday.controller;


import hamburger.fashiontoday.domain.lookstructure.LookStructure;
import hamburger.fashiontoday.domain.lookstructure.LookStructureRepository;
import hamburger.fashiontoday.domain.member.Member;
import hamburger.fashiontoday.domain.member.MemberRepository;
import hamburger.fashiontoday.domain.recommend.RecommendListInfo;
import hamburger.fashiontoday.domain.schedule.ScheduleRepository;
import hamburger.fashiontoday.domain.scheduleStatus.ScheduleStatus;
import hamburger.fashiontoday.domain.scheduleStatus.ScheduleStatusRepository;
import hamburger.fashiontoday.domain.tmplook.TmpLook;
import hamburger.fashiontoday.domain.tmplook.TmpLookInfo;
import hamburger.fashiontoday.domain.tmplook.TmpLookListInfo;
import hamburger.fashiontoday.domain.tmplook.TmpLookRepository;
import hamburger.fashiontoday.service.JwtService;
import hamburger.fashiontoday.service.S3Uploader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : 심기성
 * @version : 0.5
 * @프로그램ID : HAM-PB-1007-J
 * @프로그램명 : LookitemController.java
 * @date : 2019.10.25
 */
@RestController
@RequestMapping(value = "/recommend")
public class RecommendController {

    // 로그를 찍기 위한 Logger
    private static Logger logger = LogManager.getLogger(RecommendController.class);

    //토큰 서비스
    @Autowired
    private JwtService jwtService;

    @Autowired
    S3Uploader s3Uploader;

    @Autowired
    MemberRepository memberRepository;

    // 스케줄 상태 레파지토리
    @Autowired
    ScheduleStatusRepository scheduleStatusRepository;

    @Autowired
    TmpLookRepository tmpLookRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    LookStructureRepository lookStructureRepository;

    // 저장
    @PostMapping(value = "")
    public TmpLookInfo recommendLook(@RequestHeader(value = "Authorization") String token, @RequestParam("requestor_id") int requestorId, @RequestParam("date") String date, @RequestParam("look_img") MultipartFile multipartFile, @RequestParam("clothes_array") List<Integer> clothes, @RequestParam("look_title") String title, @RequestParam("look_introduction") String introduce) {

        int loginMemberId = 0;
        String imgUrl = new String();
        TmpLook tmpLook = new TmpLook();
        TmpLookInfo tmpLookInfo = new TmpLookInfo();

        // 로그인 여부 확인
        if (jwtService.isUsable(token)) {
            loginMemberId = jwtService.getMember(token);
            System.out.println("유저 아이디 : " + loginMemberId);

        } else {
            tmpLookInfo.setRemark("login error");
            return tmpLookInfo;
        }

        // 멀티 파트 파일 여부 확인
        if (multipartFile == null) {
            tmpLookInfo.setRemark("no multipartfile");
            return tmpLookInfo;
        }

        // 업로드
        try {
            imgUrl = s3Uploader.upload(multipartFile, String.valueOf(requestorId)+"_"+String.valueOf(loginMemberId)+"_"+date);
        } catch (Exception e) {
            tmpLookInfo.setRemark("upload error");
            return tmpLookInfo;
        }

        if (clothes.size() != 0) {
            Member loginMember = memberRepository.findByMId(loginMemberId);
            ScheduleStatus scheduleStatus = scheduleStatusRepository.findByMIdAndDdate(requestorId,date);
            tmpLook = new TmpLook(requestorId, date, imgUrl, title, introduce,loginMember);
            tmpLookRepository.save(tmpLook);
            for (int i = 0; i < clothes.size(); i++) {
                int nowLookitemId = clothes.get(i);
                LookStructure nowLookStructure = new LookStructure(requestorId, nowLookitemId, tmpLook.getTLId());
                lookStructureRepository.save(nowLookStructure);
            }
            loginMember.addReward();
            scheduleStatus.addTmpLook();
            scheduleStatusRepository.save(scheduleStatus);
            memberRepository.save(loginMember);
        } else {
            tmpLookInfo.setRemark("no Clothes_array");
            return tmpLookInfo;
        }


        return new TmpLookInfo(tmpLook);
    }


    //
    @GetMapping(value = "/list")
    public RecommendListInfo scheduleList(@RequestHeader(value = "Authorization") String token) {
        System.out.println("추천 컨트롤러");

        int loginMemberId = 0;
        int scheduleListchecker = 0;
        String nowDate = new String();
        LocalDateTime localDateTime = LocalDateTime.now();
        if (localDateTime.getMonthValue() < 10) {
            nowDate = String.valueOf(localDateTime.getYear()) +"0"+ String.valueOf(localDateTime.getMonthValue()) + String.valueOf(localDateTime.getDayOfMonth());
        } else {
            nowDate = String.valueOf(localDateTime.getYear()) + String.valueOf(localDateTime.getMonthValue()) + String.valueOf(localDateTime.getDayOfMonth());
        }
        RecommendListInfo recommendListInfo = new RecommendListInfo();

        // 로그인 여부 확인
        if (jwtService.isUsable(token)) {
            loginMemberId = jwtService.getMember(token);
            System.out.println("유저 아이디 : " + loginMemberId);

        } else {
            return recommendListInfo;
        }

        List<TmpLook> tmpLooks = tmpLookRepository.findByrecommandMId(loginMemberId);
        List<ScheduleStatus> scheduleStatusList = scheduleStatusRepository.findByMIdNotAndLeftNotOrderByLeftDesc(loginMemberId, 0);

        while (recommendListInfo.getSize() < 5 && scheduleListchecker < scheduleStatusList.size()) {
            System.out.println(scheduleListchecker);
            ScheduleStatus nowScheduleStatus = scheduleStatusList.get(scheduleListchecker);
            if(Integer.parseInt(nowScheduleStatus.getDdate())<=Integer.parseInt(nowDate)){
                scheduleListchecker++;
                continue;
            }
            boolean isRecommand = false;
            for (int i = 0; i < tmpLooks.size(); i++) {
                TmpLook nowTmpLook = tmpLooks.get(i);
                if (nowTmpLook.getDdate().equals(nowScheduleStatus.getDdate())&&nowTmpLook.getMId()==nowScheduleStatus.getMId()) {
                    if(nowTmpLook.getRecommandMId()==loginMemberId) {
                        isRecommand = true;
                        break;
                    }
                }
            }
            if (!isRecommand) {
                Member scheduleMember = memberRepository.findByMId(nowScheduleStatus.getMId());
                recommendListInfo.addSchedule(scheduleRepository.findByMIdAndDdate(nowScheduleStatus.getMId(), nowScheduleStatus.getDdate()), scheduleMember);
            }
            scheduleListchecker++;
        }


        return recommendListInfo;
    }


}
