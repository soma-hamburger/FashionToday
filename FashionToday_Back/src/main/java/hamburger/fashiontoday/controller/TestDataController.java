package hamburger.fashiontoday.controller;


import hamburger.fashiontoday.domain.lookitem.LookitemRepository;
import hamburger.fashiontoday.domain.member.Member;
import hamburger.fashiontoday.domain.member.MemberInfo;
import hamburger.fashiontoday.domain.member.MemberRepository;
import hamburger.fashiontoday.domain.schedule.ScheduleRepository;
import hamburger.fashiontoday.domain.scheduleStatus.ScheduleStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/testdata")
public class TestDataController {


    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LookitemRepository lookitemRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    ScheduleStatusRepository scheduleStatusRepository;


    @PostMapping(value = "/member")
    public MemberInfo initMember(){

        Member [] member = new Member[10];
        member[0] = new Member();




        return new MemberInfo();
    }





}
