package hamburger.fashiontoday.controller;

import hamburger.fashiontoday.domain.lookitem.ClosetInfo;
import hamburger.fashiontoday.domain.lookitem.Lookitem;
import hamburger.fashiontoday.domain.lookitem.LookitemInfo;
import hamburger.fashiontoday.domain.lookitem.LookitemRepository;
import hamburger.fashiontoday.domain.member.MemberRepository;
import hamburger.fashiontoday.service.JwtService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @author : 심기성
 * @version : 0.5
 * @프로그램ID : HAM-PB-1005-J
 * @프로그램명 : LookitemController.java
 * @date : 2019.10.15
 */
@RestController
public class LookitemController {

    // 로그를 찍기 위한 Logger
    private static Logger logger = LogManager.getLogger(LookitemController.class);

    // 로그인용 토큰을 처리하는 서비스
    @Autowired
    JwtService jwtService;

    // Member객체를 관리하는 memberrepository입니다.
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LookitemRepository lookitemRepository;

    // 302번 api
    // 룩 아이템 을 저장하는 api
    @PostMapping(value = "/lookitem")
    public LookitemInfo uploadLookitem(@RequestHeader(value = "Authorization") String token, @RequestBody Map<String, Object> param) {

        int loginMemberId = 0;
        String clothesImg = new String();
        String color = new String();
        String category = new String();
        LookitemInfo lookItemInfo = new LookitemInfo();

        // 로그인 여부 확인
        if (jwtService.isUsable(token)) {
            loginMemberId = jwtService.getMember(token);
            System.out.println("유저 아이디 : " + loginMemberId);

        } else {
            return lookItemInfo;

        }

        // 파라미터 파싱
        try {
            clothesImg = param.get("clothes_img").toString();
            color = param.get("color").toString();
            category = param.get("category").toString();

        } catch (Exception e) {
            return lookItemInfo;

        }

        Lookitem lookitem2 = new Lookitem(loginMemberId,clothesImg,color,category,clothesImg);
        System.out.println( "이거 : "+lookitem2.getMId());

        return new LookitemInfo(lookitemRepository.save(new Lookitem(loginMemberId,clothesImg,color,category,clothesImg)));
    }


    // 202번 api
    // 나만의 옷장을 불러오는 곳
    @GetMapping(value = "/closet")
    public ClosetInfo getCloset(@RequestHeader(value = "Authorization") String token) {

        int loginMemberId = 0;
        ClosetInfo closetInfo = new ClosetInfo();

        // 로그인 여부 확인
        if (jwtService.isUsable(token)) {
            loginMemberId = jwtService.getMember(token);
            System.out.println("유저 아이디 : " + loginMemberId);
        }else{
            return closetInfo;
        }

        closetInfo.setClothesList(lookitemRepository.findLookitemsByMId(loginMemberId));


        return closetInfo;
    }

}
