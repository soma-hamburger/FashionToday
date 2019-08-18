package hamburger.fashiontoday.controller;

import hamburger.fashiontoday.domain.member.Member;
import hamburger.fashiontoday.domain.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/save")
    public String process(){

        memberRepository.save(new Member(0L,"기성","totokisung@naver.com","19941003","kakao","12345678h","20190803","2019","0803","totokisung@naver.com"));

        return "Done";
    }


}