package hamburger.fashiontoday.controller;

import hamburger.fashiontoday.domain.member.Member;
import hamburger.fashiontoday.domain.member.MemberRepository;
import hamburger.fashiontoday.domain.member.MemberInfo;
import hamburger.fashiontoday.service.JwtService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
            logger.debug(programId + " : memberInfo - success : memberId = "+member.getMId());
            return memberInfo;
        }

        System.out.println("토큰 안됨");
        return memberInfo;
    }



}
