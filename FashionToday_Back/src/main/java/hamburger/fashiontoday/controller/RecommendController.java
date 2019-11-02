package hamburger.fashiontoday.controller;


import hamburger.fashiontoday.domain.member.Member;
import hamburger.fashiontoday.domain.member.MemberRepository;
import hamburger.fashiontoday.domain.recommend.RecommendListInfo;
import hamburger.fashiontoday.domain.schedule.ScheduleRepository;
import hamburger.fashiontoday.domain.scheduleStatus.ScheduleStatus;
import hamburger.fashiontoday.domain.scheduleStatus.ScheduleStatusRepository;
import hamburger.fashiontoday.domain.tmplook.TmpLook;
import hamburger.fashiontoday.domain.tmplook.TmpLookRepository;
import hamburger.fashiontoday.service.JwtService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
    MemberRepository memberRepository;

    // 스케줄 상태 레파지토리
    @Autowired
    ScheduleStatusRepository scheduleStatusRepository;

    @Autowired
    TmpLookRepository tmpLookRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    // 저장
    @PostMapping(value = "")
    public TmpLook recommandLook(@RequestHeader(value = "Authorization") String token, @RequestBody Map<String, Object> param){

        int loginMemberId = 0;
        TmpLook tmpLook = new TmpLook();


        // 로그인 여부 확인
        if (jwtService.isUsable(token)) {
            loginMemberId = jwtService.getMember(token);
            System.out.println("유저 아이디 : " + loginMemberId);

        } else {
            return new TmpLook();
        }

        return new TmpLook();
    }


    //
    @GetMapping(value = "/list")
    public RecommendListInfo scheduleList(@RequestHeader(value = "Authorization") String token, @RequestBody Map<String, Object> param) {

        int loginMemberId = 0;
        int scheduleListchecker = 0;
        String nowDate = new String();
        LocalDateTime localDateTime = LocalDateTime.now();
        if(localDateTime.getMonthValue() < 10){
            nowDate = String.valueOf(localDateTime.getYear()) + String.valueOf(localDateTime.getMonth()) + String.valueOf(localDateTime.getDayOfMonth());
        }else{
            nowDate = String.valueOf(localDateTime.getYear()) + String.valueOf(localDateTime.getMonth()) + String.valueOf(localDateTime.getDayOfMonth());
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
        List<ScheduleStatus> scheduleStatusList = scheduleStatusRepository.findByMIdNotAndLeftNotOrderByLeftDesc(loginMemberId,0);

        while(recommendListInfo.getSize()<5&&scheduleListchecker< scheduleStatusList.size()){
            ScheduleStatus nowScheduleStatus = scheduleStatusList.get(scheduleListchecker);
            boolean isRecommand = false;
            for(int i = 0; i<tmpLooks.size();i++){
                TmpLook nowTmpLook = tmpLooks.get(i);
                if(nowTmpLook.getRecommandMId()==loginMemberId){
                    isRecommand = true;
                    break;
                }
            }
            if(!isRecommand) {
                Member scheduleMember = memberRepository.findByMId(nowScheduleStatus.getMId());
                recommendListInfo.addSchedule(scheduleRepository.findByMIdAndDdate(nowScheduleStatus.getMId(), nowScheduleStatus.getDdate()),scheduleMember);
            }
            scheduleListchecker++;
        }


        return recommendListInfo;
    }

}
