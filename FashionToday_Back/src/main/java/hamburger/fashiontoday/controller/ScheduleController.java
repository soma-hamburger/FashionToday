package hamburger.fashiontoday.controller;


import hamburger.fashiontoday.domain.schedule.Schedule;
import hamburger.fashiontoday.domain.schedule.ScheduleInfo;
import hamburger.fashiontoday.domain.schedule.ScheduleListInfo;
import hamburger.fashiontoday.domain.schedule.ScheduleRepository;
import hamburger.fashiontoday.domain.scheduleStatus.ScheduleStatus;
import hamburger.fashiontoday.domain.scheduleStatus.ScheduleStatusRepository;
import hamburger.fashiontoday.service.JwtService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    // 스캐줄 레파지토리
    @Autowired
    ScheduleRepository scheduleRepository;

    // 스캐줄상태 레파지토리
    @Autowired
    ScheduleStatusRepository scheduleStatusRepository;


    @GetMapping(value = "/list")
    public ScheduleListInfo getMySchedule(@RequestHeader(value = "Authorization") String token){

        // 값
        int loginMemberId = 0;
        ScheduleListInfo scheduleListInfo = new ScheduleListInfo();

        // 로그인 여부 확인
        if (jwtService.isUsable(token)) {
            loginMemberId = jwtService.getMember(token);
            System.out.println("유저 아이디 : " + loginMemberId);

        } else {
            scheduleListInfo.setRemark("login_error");
            return scheduleListInfo;
        }

        List<Schedule> scheduleList = scheduleRepository.findByMId(loginMemberId);
        scheduleListInfo.addScheduleList(scheduleList);

        logger.debug("스케줄"+scheduleList.size());

        return new ScheduleListInfo();
    }


    // 일정 등록
    // 306 번 api
    @PostMapping(value = "")
    public ScheduleInfo postSchedule(@RequestHeader(value = "Authorization") String token, @RequestBody Map<String, Object> param) {

        // 값
        int loginMemberId = 0;
        String title = new String();
        String introduce = new String();
        String date = new String();
        int star = 0;
        Schedule uploadSchdule;
        ScheduleStatus uploadScheduleStatus;
        ScheduleInfo scheduleInfo;

        // 로그인 여부 확인
        if (jwtService.isUsable(token)) {
            loginMemberId = jwtService.getMember(token);
            System.out.println("유저 아이디 : " + loginMemberId);

        } else {
            return new ScheduleInfo("error_login");
        }

        // 파라미터 파싱
        try {
            title = param.get("title").toString();
            introduce = param.get("introduce").toString();
            star = Integer.parseInt(param.get("star").toString());
            date = param.get("date").toString();
        } catch (Exception e) {
            return new ScheduleInfo("error_param");
        }

        // 스캐줄 저장 할 값 세팅
        uploadSchdule = new Schedule(loginMemberId, date, title, introduce, star);
        uploadScheduleStatus = new ScheduleStatus(uploadSchdule);

        //저장하는 곳
        try {
            scheduleInfo = new ScheduleInfo(scheduleRepository.save(uploadSchdule));
            scheduleStatusRepository.save(uploadScheduleStatus);
        } catch (Exception e) {
            return new ScheduleInfo("error_save");
        }

        return scheduleInfo;
    }




}
