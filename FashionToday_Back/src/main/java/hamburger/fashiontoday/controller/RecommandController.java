package hamburger.fashiontoday.controller;


import hamburger.fashiontoday.domain.schedule.Schedule;
import hamburger.fashiontoday.domain.schedule.ScheduleInfo;
import hamburger.fashiontoday.domain.schedule.ScheduleRepository;
import hamburger.fashiontoday.domain.scheduleStatus.ScheduleStatusRepository;
import hamburger.fashiontoday.domain.tmplook.TmpLook;
import hamburger.fashiontoday.service.JwtService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author : 심기성
 * @version : 0.5
 * @프로그램ID : HAM-PB-1007-J
 * @프로그램명 : LookitemController.java
 * @date : 2019.10.25
 */
@RestController
@RequestMapping(value = "/recommand")
public class RecommandController {

    // 로그를 찍기 위한 Logger
    private static Logger logger = LogManager.getLogger(RecommandController.class);

    //토큰 서비스
    @Autowired
    private JwtService jwtService;

    // 스케줄 상태 레파지토리
    @Autowired
    ScheduleStatusRepository scheduleStatusRepository;

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

    @GetMapping(value = "/list")
    public ScheduleInfo scheduleList(@RequestHeader(value = "Authorization") String token, @RequestBody Map<String, Object> param) {

        int loginMemberId = 0;
        String title = new String();
        String introduce = new String();
        String date = new String();
        int star = 0;
        Schedule uploadSchdule;
        ScheduleInfo scheduleInfo = new ScheduleInfo();

        // 로그인 여부 확인
        if (jwtService.isUsable(token)) {
            loginMemberId = jwtService.getMember(token);
            System.out.println("유저 아이디 : " + loginMemberId);

        } else {
            return scheduleInfo;
        }




        return scheduleInfo;
    }

}
