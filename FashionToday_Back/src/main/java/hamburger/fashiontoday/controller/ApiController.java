package hamburger.fashiontoday.controller;

import hamburger.fashiontoday.domain.member.Member;
import hamburger.fashiontoday.domain.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/save")
    public String process(){
        memberRepository.save(new Member("기성", "totokisung@naver.com", "19941105", "kakao", "123456789h", "totokisung@naver.com", 0, "http://fashiontoday.com/profile/1", "한줄 상태 매세지", "20190826090630", "20190826", "090630", "20190826"));
        return "Done";
    }

    @GetMapping("/load")
    public Member loadMember(){
        Member member = memberRepository.findByMId((long)3);
        return member;
    }

}
