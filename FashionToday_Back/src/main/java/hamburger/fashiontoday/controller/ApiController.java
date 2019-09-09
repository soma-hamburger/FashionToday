package hamburger.fashiontoday.controller;

import hamburger.fashiontoday.domain.member.Member;
import hamburger.fashiontoday.domain.member.MemberRepository;
import hamburger.fashiontoday.dto.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

// Api 개발용 컨트롤러 입니다.
@RestController
public class ApiController {

    // Member객체를 관리하는 memberrepository입니다.
    @Autowired
    MemberRepository memberRepository;

//    // 개발용 memberdata를 집어넣기 위한 매소드 입니다.
//    @GetMapping("/save")
//    public String process(){
//        memberRepository.save(new Member("기성", "totokisung@naver.com", "19941105", "kakao", "123456789h", "totokisung@naver.com", 0, "http://fashiontoday.com/profile/1", "한줄 상태 매세지", "20190826090630", "20190826", "090630", "20190826"));
//        return "Done";
//    }

    // 개발용 memberdata를 꺼내오는 매소드 입니다,
    @GetMapping("/load")
    public Member loadMember(){
        Member member = memberRepository.findByMId(3);
        return member;
    }

    @PostMapping("/userInfo")
    public UserInfo userInfo(){

        Member member = memberRepository.findByMId(3);
        UserInfo userInfo = new UserInfo(member.getMId(),member.getMName(),member.getMStar(),member.getMProfileUrl(),10,"200");

        return userInfo;
    }


}
