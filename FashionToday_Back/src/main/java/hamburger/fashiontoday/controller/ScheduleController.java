package hamburger.fashiontoday.controller;


import hamburger.fashiontoday.domain.look.Look;
import hamburger.fashiontoday.domain.look.LookDetailInfo;
import hamburger.fashiontoday.domain.look.LookRepository;
import hamburger.fashiontoday.domain.lookitem.Lookitem;
import hamburger.fashiontoday.domain.lookitem.LookitemRepository;
import hamburger.fashiontoday.domain.lookstructure.LookStructure;
import hamburger.fashiontoday.domain.lookstructure.LookStructureRepository;
import hamburger.fashiontoday.domain.member.Member;
import hamburger.fashiontoday.domain.member.MemberRepository;
import hamburger.fashiontoday.domain.schedule.*;
import hamburger.fashiontoday.domain.scheduleStatus.ScheduleStatus;
import hamburger.fashiontoday.domain.scheduleStatus.ScheduleStatusRepository;
import hamburger.fashiontoday.domain.tmplook.TmpLook;
import hamburger.fashiontoday.domain.tmplook.TmpLookRepository;
import hamburger.fashiontoday.service.JwtService;
import jdk.nashorn.internal.runtime.options.Option;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    //유저 레파지토리
    @Autowired
    MemberRepository memberRepository;

    // 스캐줄 레파지토리
    @Autowired
    ScheduleRepository scheduleRepository;

    // 스캐줄상태 레파지토리
    @Autowired
    ScheduleStatusRepository scheduleStatusRepository;

    @Autowired
    TmpLookRepository tmpLookRepository;

    @Autowired
    LookRepository lookRepository;

    @Autowired
    LookStructureRepository lookStructureRepository;

    @Autowired
    LookitemRepository lookitemRepository;


    @GetMapping(value = "/list")
    public ScheduleListInfo getMySchedule(@RequestHeader(value = "Authorization") String token) {

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

        return scheduleListInfo;
    }

    @PostMapping(value = "/detail")
    public ScheduleDetailInfo getScheduleDeatil(@RequestHeader(value = "Authorization") String token, @RequestBody Map<String, Object> param) {

        // 값
        int loginMemberId = 0;
        ScheduleDetailInfo scheduleDetailInfo = new ScheduleDetailInfo();
        String date = new String();

        String nowDate = new String();
        LocalDateTime localDateTime = LocalDateTime.now();
        if (localDateTime.getMonthValue() < 10) {
            nowDate = String.valueOf(localDateTime.getYear()) +"0"+ String.valueOf(localDateTime.getMonthValue()) + String.valueOf(localDateTime.getDayOfMonth());
        } else {
            nowDate = String.valueOf(localDateTime.getYear()) + String.valueOf(localDateTime.getMonthValue()) + String.valueOf(localDateTime.getDayOfMonth());
        }

        // 로그인 여부 확인
        if (jwtService.isUsable(token)) {
            loginMemberId = jwtService.getMember(token);
            System.out.println("유저 아이디 : " + loginMemberId);

        } else {
            scheduleDetailInfo.setRemark("error_login");
            return scheduleDetailInfo;
        }

        // 파라미터 파싱
        try {
            date = param.get("date").toString();
        } catch (Exception e) {
            scheduleDetailInfo.setRemark("error_param");
            return scheduleDetailInfo;
        }

        try {
            Schedule schedule = scheduleRepository.findByMIdAndDdate(loginMemberId,date);

            if(schedule == null){
                scheduleDetailInfo.setState("no_data");
                return scheduleDetailInfo;
            }

            // 옷 선택
            if(schedule.getSelect()==0){
                scheduleDetailInfo.unSelect(schedule);
                return scheduleDetailInfo;
            }

            // 과거의 상태
            else{
                scheduleDetailInfo.setPast(schedule);
                Look dailyLook = lookRepository.findByKId(schedule.getKId());
                TmpLook tmpLook = tmpLookRepository.findByTLId(dailyLook.getTlid());
                List<LookStructure> lookStructures = lookStructureRepository.findLookStructuresByTlId(tmpLook.getTLId());
                List<Lookitem> lookitems = new ArrayList<>();
                for (LookStructure lookStructure : lookStructures) {
                    lookitems.add(lookitemRepository.findByKmId(lookStructure.getKmId()));
                }
                System.out.println(6);
                scheduleDetailInfo = new ScheduleDetailInfo(schedule, dailyLook, tmpLook, lookitems);
                return scheduleDetailInfo;
            }

        }catch (Exception e){
            scheduleDetailInfo.setRemark("error_data");
            return scheduleDetailInfo;
        }


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
        Member loginMember = new Member();

        // 로그인 여부 확인
        if (jwtService.isUsable(token)) {
            loginMemberId = jwtService.getMember(token);
            System.out.println("유저 아이디 : " + loginMemberId);
            loginMember = memberRepository.findByMId(loginMemberId);

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
            loginMember.useStar(star);
            scheduleInfo = new ScheduleInfo(scheduleRepository.save(uploadSchdule));
            scheduleStatusRepository.save(uploadScheduleStatus);
            memberRepository.save(loginMember);
        } catch (Exception e) {
            return new ScheduleInfo("error_save");
        }

        return scheduleInfo;
    }


}
