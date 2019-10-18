package hamburger.fashiontoday.controller;


import hamburger.fashiontoday.domain.schedule.Schedule;
import hamburger.fashiontoday.domain.schedule.ScheduleInfo;
import hamburger.fashiontoday.domain.schedule.ScheduleRepository;
import hamburger.fashiontoday.service.JwtService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author : 심기성
 * @version : 0.6
 * @프로그램ID : HAM-PB-1006-J
 * @프로그램명 : ScheduleController.java
 * @date : 2019.10.15
 */
@RestController
@RequestMapping(value = "/schedule")
public class ScheduleController {

    // 로그를 찍기 위한 Logger
    private static Logger logger = LogManager.getLogger(ScheduleController.class);

    //토큰 서비스
    @Autowired
    private JwtService jwtService;

    @Autowired
    ScheduleRepository scheduleRepository;

    @PostMapping(value = "")
    public ScheduleInfo uploadSchedule(@RequestHeader(value = "Authorization") String token,@RequestBody Map<String, Object> param) {

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

        // 파라미터 파싱
        try {
            title = param.get("title").toString();
            introduce = param.get("introduce").toString();
            star = Integer.parseInt(param.get("star").toString());
            date = param.get("date").toString();
        } catch (Exception e) {
            return scheduleInfo;
        }

        uploadSchdule = new Schedule(loginMemberId,date,title,introduce,star);
        scheduleInfo.getSchedule(scheduleRepository.save(uploadSchdule));

        return scheduleInfo;
    }


}
