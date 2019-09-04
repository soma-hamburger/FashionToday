package hamburger.fashiontoday.controller;

import hamburger.fashiontoday.domain.member.Member;
import hamburger.fashiontoday.domain.member.MemberRepository;
import hamburger.fashiontoday.service.KakaoAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;


/**
 *
 * @author : 심기성
 * @version : PB-1000-J
 * @date : 2019.09.01
 * 
 */
@Controller
public class LoginController {

    //로그인 토큰을 받아주는 카카오API서비스
    @Autowired
    private KakaoAPI kakaoAPI;

    //맴버 레파지 토리
    @Autowired
    MemberRepository memberRepository;

    // index페이지를 내려주는 메소드
    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    // 로그인 요청을 담당하는 메소드
    // 로그인 이후 사용자 코드를 받아 토큰을 반환함
    @RequestMapping(value = "/login")
    public String kakaoLogin(@RequestParam("code") String code, HttpSession session) {
        // 유저 코드로 토큰을 받아오는 작업
        String access_Token = kakaoAPI.getAccessToken(code);
        System.out.println(this.getClass().getName() + " / kakaoLogin / controller access_token : " + access_Token);
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

        // 유저 세션
        if (userInfo.get("email") != null) {
            session.setAttribute(this.getClass().getName() + " / kakaoLogin / userId", userInfo.get("email"));
            session.setAttribute(this.getClass().getName() + " / kakaoLogin / access_Token", access_Token);
        }

        return "index";
    }

}