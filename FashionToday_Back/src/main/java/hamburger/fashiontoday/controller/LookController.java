package hamburger.fashiontoday.controller;


import hamburger.fashiontoday.domain.look.Look;
import hamburger.fashiontoday.domain.look.LookDetailInfo;
import hamburger.fashiontoday.domain.look.LookListInfo;
import hamburger.fashiontoday.domain.look.LookRepository;
import hamburger.fashiontoday.domain.lookitem.Lookitem;
import hamburger.fashiontoday.domain.lookitem.LookitemRepository;
import hamburger.fashiontoday.domain.lookstructure.LookStructure;
import hamburger.fashiontoday.domain.lookstructure.LookStructureRepository;
import hamburger.fashiontoday.domain.member.Member;
import hamburger.fashiontoday.domain.member.MemberRepository;
import hamburger.fashiontoday.domain.schedule.Schedule;
import hamburger.fashiontoday.domain.schedule.ScheduleRepository;
import hamburger.fashiontoday.domain.tmplook.TmpLook;
import hamburger.fashiontoday.domain.tmplook.TmpLookInfo;
import hamburger.fashiontoday.domain.tmplook.TmpLookListInfo;
import hamburger.fashiontoday.domain.tmplook.TmpLookRepository;
import hamburger.fashiontoday.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "")
public class LookController {

    @Autowired
    JwtService jwtService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TmpLookRepository tmpLookRepository;

    @Autowired
    LookRepository lookRepository;

    @Autowired
    LookStructureRepository lookStructureRepository;

    @Autowired
    LookitemRepository lookitemRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    //203
    @GetMapping(value = "/dailylist")
    public TmpLookListInfo dailyList(@RequestHeader(value = "Authorization") String token) {

        int loginMemberId = 0;

        String nowDate = new String();
        LocalDateTime localDateTime = LocalDateTime.now();
        if (localDateTime.getMonthValue() < 10) {
            nowDate = String.valueOf(localDateTime.getYear()) + String.valueOf(localDateTime.getMonthValue()) + String.valueOf(localDateTime.getDayOfMonth());
        } else {
            nowDate = String.valueOf(localDateTime.getYear()) + String.valueOf(localDateTime.getMonthValue()) + String.valueOf(localDateTime.getDayOfMonth());
        }
        TmpLookListInfo tmpLookListInfo = new TmpLookListInfo();

        // 로그인 여부 확인
        if (jwtService.isUsable(token)) {
            loginMemberId = jwtService.getMember(token);
            System.out.println("유저 아이디 : " + loginMemberId);

        } else {
            tmpLookListInfo.setRemark("login_error");
            return tmpLookListInfo;
        }

        System.out.println(nowDate);
        List<TmpLook> todayTmpLooks = tmpLookRepository.findByMIdAndDdate(loginMemberId, nowDate);
        if (todayTmpLooks.size() > 0) {
            for (int i = 0; i < todayTmpLooks.size(); i++) {
                tmpLookListInfo.addTmpLook(todayTmpLooks.get(i));
            }
        } else {
            tmpLookListInfo.setRemark("no_looks");
            return tmpLookListInfo;
        }
        return tmpLookListInfo;
    }

    // 303 번
    @PostMapping(value = "/look/choice")
    public TmpLookInfo choiceLook(@RequestHeader(value = "Authorization") String token, @RequestBody Map<String, Object> param) {

        Member loginMember = new Member();
        int loginMemberId = 0;
        int lookId = 0;
        String nowDate = new String();
        LocalDateTime localDateTime = LocalDateTime.now();
        if (localDateTime.getMonthValue() < 10) {
            nowDate = String.valueOf(localDateTime.getYear()) + String.valueOf(localDateTime.getMonthValue()) + String.valueOf(localDateTime.getDayOfMonth());
        } else {
            nowDate = String.valueOf(localDateTime.getYear()) + String.valueOf(localDateTime.getMonthValue()) + String.valueOf(localDateTime.getDayOfMonth());
        }
        TmpLookInfo tmpLookInfo = new TmpLookInfo();

        // 로그인 여부 확인
        if (jwtService.isUsable(token)) {
            loginMemberId = jwtService.getMember(token);
            loginMember = memberRepository.findByMId(loginMemberId);
            System.out.println("유저 아이디 : " + loginMemberId);
        } else {
            tmpLookInfo.setRemark("login_error");
            return tmpLookInfo;
        }


        try {
            lookId = Integer.parseInt(param.get("look_id").toString());
        } catch (NullPointerException e) {
            tmpLookInfo.setRemark("param_error");
            return tmpLookInfo;
        }

        TmpLook choiceTmpLook = tmpLookRepository.findByTLId(lookId);
        Look choiceLook = new Look(choiceTmpLook);
        loginMember.setMSelectdate(nowDate);
        Schedule schedule = scheduleRepository.findByMIdAndDdate(loginMemberId,nowDate);
        schedule.setKId(lookRepository.save(choiceLook).getKId());
        schedule.setSelect(1);
        scheduleRepository.save(schedule);
        memberRepository.save(loginMember);
        tmpLookInfo.setRemark("success");

        return tmpLookInfo;
    }


    // 208번
    @PostMapping(value = "/dailylook")
    public LookDetailInfo lookDetailInfo(@RequestHeader(value = "Authorization") String token, @RequestBody Map<String, Object> param) {

        int lookId = 0;
        int loginMemberId = 0;
        LookDetailInfo lookDetailInfo = new LookDetailInfo();

        // 로그인 여부 확인
        if (jwtService.isUsable(token)) {
            loginMemberId = jwtService.getMember(token);
            System.out.println("유저 아이디 : " + loginMemberId);
        } else {
            lookDetailInfo.setRemark("login_error");
            return lookDetailInfo;
        }

        try {
            lookId = Integer.parseInt(param.get("look_id").toString());
        } catch (NullPointerException e) {
            lookDetailInfo.setRemark("param_error");
            return lookDetailInfo;
        }

        System.out.println("룩 아이디" + lookId);

        TmpLook tmpLook = tmpLookRepository.findByTLId(lookId);
        List<LookStructure> lookStructures = lookStructureRepository.findLookStructuresByTlId(tmpLook.getTLId());
        List<Lookitem> lookitems = new ArrayList<>();
        for (LookStructure lookStructure : lookStructures) {
            lookitems.add(lookitemRepository.findByKmId(lookStructure.getKmId()));
        }
        lookDetailInfo = new LookDetailInfo(tmpLook, lookitems);

        return lookDetailInfo;
    }


    // 204번
    // tmpLook 상새정보
    @PostMapping(value = "/look")
    public LookDetailInfo tmplookDetailInfo(@RequestHeader(value = "Authorization") String token, @RequestBody Map<String, Object> param) {

        int lookId = 0;
        int loginMemberId = 0;

        LookDetailInfo lookDetailInfo = new LookDetailInfo();

        // 로그인 여부 확인
        if (jwtService.isUsable(token)) {
            loginMemberId = jwtService.getMember(token);
            System.out.println("유저 아이디 : " + loginMemberId);
        } else {
            lookDetailInfo.setRemark("login_error");
            return lookDetailInfo;
        }

        try {
            lookId = Integer.parseInt(param.get("look_id").toString());
        } catch (NullPointerException e) {
            lookDetailInfo.setRemark("param_error");
            return lookDetailInfo;
        }

        System.out.println("룩 아이디" + lookId);

        Look dailyLook = lookRepository.findByKId(lookId);
        TmpLook tmpLook = tmpLookRepository.findByTLId(dailyLook.getTlid());
        Member targetMember = memberRepository.findByMId(tmpLook.getMId());
        List<LookStructure> lookStructures = lookStructureRepository.findLookStructuresByTlId(tmpLook.getTLId());
        List<Lookitem> lookitems = new ArrayList<>();
        for (LookStructure lookStructure : lookStructures) {
            lookitems.add(lookitemRepository.findByKmId(lookStructure.getKmId()));
        }
        lookDetailInfo = new LookDetailInfo(dailyLook,targetMember, tmpLook, lookitems);


        return lookDetailInfo;
    }


    //207번
    @GetMapping(value = "/looklist")
    public LookListInfo lookListInfo() {

        LookListInfo lookListInfo = new LookListInfo();

        List<Look> lookList = lookRepository.findAll();
        for (Look nowLook : lookList) {
            if(nowLook.getKShare()==0){
                continue;
            }
            TmpLook tmpLook = tmpLookRepository.findByTLId(nowLook.getTlid());
            Member lookMember = memberRepository.findByMId(tmpLook.getMId());
            lookListInfo.addLook(nowLook.getKId(), lookMember, tmpLook);
        }

        return lookListInfo;
    }


    @PostMapping(value="/look/share")
    public LookDetailInfo lookShare(@RequestHeader(value = "Authorization") String token, @RequestBody Map<String, Object> param){


        int lookId = 0;
        String date = "";
        int loginMemberId = 0;

        LookDetailInfo lookDetailInfo = new LookDetailInfo();

        // 로그인 여부 확인
        if (jwtService.isUsable(token)) {
            loginMemberId = jwtService.getMember(token);
            System.out.println("유저 아이디 : " + loginMemberId);
        } else {
            lookDetailInfo.setRemark("login_error");
            return lookDetailInfo;
        }

        try {
            lookId = Integer.parseInt(param.get("look_id").toString());
            date = param.get("date").toString();
        } catch (NullPointerException e) {
            lookDetailInfo.setRemark("param_error");
            return lookDetailInfo;
        }

        Look look = lookRepository.findByKId(lookId);
        look.setKShare(1);
        lookRepository.save(look);

        return new LookDetailInfo();
    }



}
