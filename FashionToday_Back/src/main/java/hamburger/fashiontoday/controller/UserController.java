package hamburger.fashiontoday.controller;

import hamburger.fashiontoday.domain.member.Member;
import hamburger.fashiontoday.domain.member.MemberDetailInfo;
import hamburger.fashiontoday.domain.member.MemberRepository;
import hamburger.fashiontoday.domain.member.MemberInfo;
import hamburger.fashiontoday.domain.tmplook.TmpLook;
import hamburger.fashiontoday.domain.tmplook.TmpLookRepository;
import hamburger.fashiontoday.service.JwtService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


/**
 * @프로그램ID : HAM-PB-1002-J
 * @프로그램명 : UserController.java
 * @author : 심기성
 * @date : 2019.09.29
 * @version : 0.5
 *
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

    // 로그를 찍기 위한 Logger
    private static Logger logger = LogManager.getLogger(UserController.class);

    // 프로그램 아이디 및 에러 코드
    private String programId = "HAM-PB-4002-J";
    private String errorCode = "";

    @Autowired
    JwtService jwtService;

    // Member객체를 관리하는 memberrepository입니다.
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TmpLookRepository tmpLookRepository;

    /**
     *
     * @return
     */
    @PostMapping("/info")
    public MemberInfo userInfo(@RequestHeader(value = "Authorization")String authorization){

        System.out.println("나의 토큰 :"+authorization);

        if(jwtService.isUsable(authorization)){
            System.out.println("유저 아이디 : "+jwtService.getMember(authorization));
            Member member = memberRepository.findByMId(jwtService.getMember(authorization));
            MemberInfo memberInfo = new MemberInfo(member.getMId(),member.getMName(),member.getMStar(),member.getMProfileUrl(),10,"200");

            String nowDate = new String();
            LocalDateTime localDateTime = LocalDateTime.now();
            if (localDateTime.getMonthValue() < 10) {
                nowDate = String.valueOf(localDateTime.getYear()) + "0" + String.valueOf(localDateTime.getMonthValue()) + String.valueOf(localDateTime.getDayOfMonth());
            } else {
                nowDate = String.valueOf(localDateTime.getYear()) + String.valueOf(localDateTime.getMonthValue()) + String.valueOf(localDateTime.getDayOfMonth());
            }

            // 오늘 선택이 되지 않았을 경우
            if(Integer.parseInt(member.getMSelectdate())< Integer.parseInt(nowDate)){
                List<TmpLook> tmpLookList = tmpLookRepository.findByMIdAndDdate(member.getMId(),nowDate);
                if(tmpLookList.size()>0){
                       memberInfo.unSelect();
                }
            }

            logger.debug(programId + " : memberInfo - success : memberId = "+member.getMId());
            return memberInfo;
        }

        System.out.println("토큰 안됨");
        return new MemberInfo();
    }

    @GetMapping(value = "/detail")
    public MemberDetailInfo detailInfo(@RequestBody Map<String, Object> param){

        int userId = 0;
        MemberDetailInfo memberInfo = new MemberDetailInfo();
        Member member = new Member();

        try {
            userId = Integer.parseInt(param.get("user_id").toString());
        } catch (Exception e) {
            memberInfo.setRemark("param_error");
            return memberInfo;
        }

        try{
            member = memberRepository.findByMId(userId);
        }catch (Exception e){
            memberInfo.setRemark("no_user");
        }

        memberInfo.setMember(member);

        return memberInfo;
    }


}
