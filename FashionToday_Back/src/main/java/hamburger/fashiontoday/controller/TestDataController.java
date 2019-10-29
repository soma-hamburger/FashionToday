package hamburger.fashiontoday.controller;


import hamburger.fashiontoday.domain.lookitem.LookitemRepository;
import hamburger.fashiontoday.domain.member.Member;
import hamburger.fashiontoday.domain.member.MemberInfo;
import hamburger.fashiontoday.domain.member.MemberRepository;
import hamburger.fashiontoday.domain.schedule.ScheduleRepository;
import hamburger.fashiontoday.domain.scheduleStatus.ScheduleStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/testdata")
public class TestDataController {


    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LookitemRepository lookitemRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    ScheduleStatusRepository scheduleStatusRepository;


    @PostMapping(value = "/member")
    public MemberInfo initMember(){

        Member [] member = new Member[10];
        member[0] = new Member(1,"심기성","totokisung@naver.com","19941003","kakao","12345678",100,100,2,10,"https://search.pstatic.net/common?type=a&size=120x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201901%2F20190107105322719.jpg","사랑과 정렬을 그대에게");
        member[1] = new Member(2,"오원석","ows3080@gmail.com","19951007","kakao","12345678",100,100,2,10,"https://search.pstatic.net/common?type=a&size=120x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201904%2F20190405134441791.jpg","어플리케이션 마스터");
        member[2] = new Member(3,"장동훈","dhoonjang@gmail.com","19990803","kakao","12345678",100,100,2,10,"https://search.pstatic.net/common?type=a&size=120x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2F76%2F201706081735556361.jpg","충 성!");
        member[3] = new Member(4,"손나은","sonnaeun@naver.com","19940210","kakao","12345678",100,100,2,10,"https://search.pstatic.net/common?type=a&size=120x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201905%2F20190503184715926.jpg","이건 맛의 대.참.치.");
        member[4] = new Member(5,"오하영","hyojuhan@naver.com","19960719","kakao","12345678",100,100,2,10,"https://search.pstatic.net/common?type=a&size=120x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201908%2F20190816123210681.jpg","1도 없어 예전의 느낌");
        member[5] = new Member(6,"모모","peachtree@naver.com","19961109","kakao","12345678",100,100,2,10,"https://search.pstatic.net/common?type=a&size=120x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201909%2F20190911124635987-5025054.jpg","조르지마~");
        member[6] = new Member(7,"아이유","childrenU@naver.com","19930516","kakao","12345678",100,100,2,10,"https://search.pstatic.net/common?type=a&size=120x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201812%2F20181217143510269.jpg","나는요~ 오빠가 좋은걸~");
        member[7] = new Member(8,"태연","girsGeneration@naver.com","19890309","kakao","12345678",100,100,2,10,"https://search.pstatic.net/common?type=a&size=120x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201903%2F20190326182402343-4929626.jpg","소원을 말해봐");
        member[8] = new Member(9,"나연","twiceNa@naver.com","19950922","kakao","12345678",100,100,2,10,"https://search.pstatic.net/common?type=a&size=120x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201909%2F20190911124406327-9808989.jpg","T. T.");
        member[9] = new Member(10,"사나","fourme@naver.com","19961229","kakao","12345678",100,100,2,10,"https://search.pstatic.net/common?type=a&size=120x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201909%2F20190914124723164-9958359.jpg","친구를 만나느라 샤.샤.샤.");

        for(int i = 0 ; i<10;i++){
          memberRepository.save(member[i]);
        }

        return new MemberInfo();
    }





}
