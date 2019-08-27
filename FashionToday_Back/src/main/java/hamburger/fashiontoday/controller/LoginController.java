package hamburger.fashiontoday.controller;

import hamburger.fashiontoday.service.KakaoAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 로그인 뷰를 내려주는 컨트롤러 입니다.
@Controller
public class LoginController {

    //로그인 토큰을 받아주는 카카오API서비스
    @Autowired
    private KakaoAPI kakaoAPI;

    // index페이지를 내려주는 메소드
    @RequestMapping(value="/")
    public String index() {

        return "index";
    }

    // 로그인 요청을 담당하는 메소드
    // 로그인 이후 사용자 코드를 받아 토큰을 반환함
    @RequestMapping(value="/login")
    public String login(@RequestParam("code") String code) {
        String access_Token = kakaoAPI.getAccessToken(code);
        System.out.println("controller access_token : " + access_Token);

        return "index";
    }



}