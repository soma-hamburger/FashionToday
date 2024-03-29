package hamburger.fashiontoday.controller;

import hamburger.fashiontoday.domain.member.Member;
import hamburger.fashiontoday.domain.member.MemberRepository;
import hamburger.fashiontoday.domain.member.LoginInfo;
import hamburger.fashiontoday.service.JwtService;
import hamburger.fashiontoday.service.KakaoAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * @프로그램ID : HAM-PB-1001-J
 * @프로그램명 : LoginController.java
 * @author : 심기성
 * @date : 2019.10.08
 * @version : 0.6
 *
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    // 로그를 찍기 위한 Logger
    private static Logger logger = LogManager.getLogger(LoginController.class);

    //로그인 토큰을 받아주는 카카오API서비스
    @Autowired
    private KakaoAPI kakaoAPI;

    //맴버 레파지 토리
    @Autowired
    MemberRepository memberRepository;

    //토큰 서비스
    @Autowired
    private JwtService jwtService;

    // 로그인 요청을 담당하는 메소드
    // 로그인 이후 사용자 코드를 받아 토큰을 반환함
    @PostMapping(value = "/kakao")
    public LoginInfo kakaoLogin(@RequestBody Map<String, Object> param) {

        String code = param.get("code").toString();

        //파라미터 확인
        logger.debug(this.getClass().getName() + " param : " + code);

        // 유저 코드로 토큰을 받아오는 작업
        String access_Token = kakaoAPI.getAccessToken(code);
        logger.debug(this.getClass().getName() + " / kakaoLogin / controller access_token : " + access_Token);
        HashMap<String, Object> userInfo = kakaoAPI.getUserInfo(access_Token);

        // 아이디 값 할당
        int userId = Integer.parseInt(userInfo.get("id").toString());

        // 유저 회원 가입이 안되어 있을경우 회원 가입이 되며
        // 회원 가입이 되어있다면 업데이트
        Member loginMember = new Member(userInfo);
        if (memberRepository.findByMId(userId) != null) {
            loginMember = memberRepository.findByMId(userId);
            loginMember.update(userInfo);
        }
        memberRepository.save(loginMember);

        try {
            String token = jwtService.create("member", loginMember, "user");
            //response.setHeader("Authorization", token);
            return new LoginInfo(token);
        }catch (Exception e){

        }

        return new LoginInfo("fail");
    }

    @PostMapping(value = "/kakaotest")
    public LoginInfo kakaoTest(HttpServletResponse response) {

        System.out.println("시작");

        int userId = 3;
        Member loginMember = new Member();
        loginMember = memberRepository.findByMId(userId);

        try {
            String token = jwtService.create("member", loginMember, "user");
           // response.setHeader("Authorization", token);
            return new LoginInfo(token);

        }catch (Exception e){

            return new LoginInfo("fail");

        }
    }

}