package hamburger.fashiontoday.controller;


import hamburger.fashiontoday.domain.lookitem.Lookitem;
import hamburger.fashiontoday.domain.lookitem.LookitemInfo;
import hamburger.fashiontoday.domain.lookitem.LookitemRepository;
import hamburger.fashiontoday.domain.member.LoginInfo;
import hamburger.fashiontoday.domain.member.Member;
import hamburger.fashiontoday.domain.member.MemberInfo;
import hamburger.fashiontoday.domain.member.MemberRepository;
import hamburger.fashiontoday.domain.schedule.ScheduleRepository;
import hamburger.fashiontoday.domain.scheduleStatus.ScheduleStatusRepository;
import hamburger.fashiontoday.service.JwtService;
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

    @Autowired
    JwtService jwtService;


    @PostMapping(value = "/member")
    public MemberInfo initMember() {

        Member[] member = new Member[10];
        member[0] = new Member(1, "심기성", "totokisung@naver.com", "19941003", "kakao", "12345678", 100, 100, 2, 10, "https://search.pstatic.net/common?type=a&size=120x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201901%2F20190107105322719.jpg", "사랑과 정렬을 그대에게");
        member[1] = new Member(2, "오원석", "ows3080@gmail.com", "19951007", "kakao", "12345678", 100, 100, 2, 9, "https://search.pstatic.net/common?type=a&size=120x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201904%2F20190405134441791.jpg", "어플리케이션 마스터");
        member[2] = new Member(3, "장동훈", "dhoonjang@gmail.com", "19990803", "kakao", "12345678", 100, 100, 2, 8, "https://search.pstatic.net/common?type=a&size=120x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2F76%2F201706081735556361.jpg", "충 성!");
        member[3] = new Member(4, "손나은", "sonnaeun@naver.com", "19940210", "kakao", "12345678", 100, 100, 1, 7, "https://search.pstatic.net/common?type=a&size=120x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201905%2F20190503184715926.jpg", "이건 맛의 대.참.치.");
        member[4] = new Member(5, "오하영", "hyojuhan@naver.com", "19960719", "kakao", "12345678", 100, 100, 1, 6, "https://search.pstatic.net/common?type=a&size=120x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201908%2F20190816123210681.jpg", "1도 없어 예전의 느낌");
        member[5] = new Member(6, "모모", "peachtree@naver.com", "19961109", "kakao", "12345678", 100, 100, 1, 5, "https://search.pstatic.net/common?type=a&size=120x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201909%2F20190911124635987-5025054.jpg", "조르지마~");
        member[6] = new Member(7, "아이유", "childrenU@naver.com", "19930516", "kakao", "12345678", 100, 100, 1, 4, "https://search.pstatic.net/common?type=a&size=120x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201812%2F20181217143510269.jpg", "나는요~ 오빠가 좋은걸~");
        member[7] = new Member(8, "태연", "girsGeneration@naver.com", "19890309", "kakao", "12345678", 100, 100, 1, 3, "https://search.pstatic.net/common?type=a&size=120x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201903%2F20190326182402343-4929626.jpg", "소원을 말해봐");
        member[8] = new Member(9, "나연", "twiceNa@naver.com", "19950922", "kakao", "12345678", 100, 100, 1, 2, "https://search.pstatic.net/common?type=a&size=120x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201909%2F20190911124406327-9808989.jpg", "T. T.");
        member[9] = new Member(10, "사나", "fourme@naver.com", "19961229", "kakao", "12345678", 100, 100, 1, 1, "https://search.pstatic.net/common?type=a&size=120x150&quality=95&direct=true&src=http%3A%2F%2Fsstatic.naver.net%2Fpeople%2Fportrait%2F201909%2F20190914124723164-9958359.jpg", "친구를 만나느라 샤.샤.샤.");

        for (int i = 0; i < 10; i++) {
            memberRepository.save(member[i]);
        }

        return new MemberInfo();
    }

    @PostMapping(value = "/lookitem")
    public LookitemInfo initLookitem() {

        Lookitem[][] lookitem = new Lookitem[10][10];

        lookitem[0][0] = new Lookitem(1, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/sample/lookitem/coat1.jpeg", "brown", "coat", "");
        lookitem[0][1] = new Lookitem(1, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/sample/lookitem/hood1.jpeg", "black", "hood", "");
        lookitem[0][2] = new Lookitem(1, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/sample/lookitem/pants1.jpeg", "brown", "pants", "");
        lookitem[0][3] = new Lookitem(1, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/sample/lookitem/pants2.jpeg", "blue", "pants", "");
        lookitem[0][4] = new Lookitem(1, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/sample/lookitem/pants3.jpeg", "yellow", "pants", "");
        lookitem[0][5] = new Lookitem(1, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/sample/lookitem/shirts1.jpeg", "white", "shirts", "");
        lookitem[0][6] = new Lookitem(1, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/sample/lookitem/shoes1.jpeg", "white", "shoes", "");
        lookitem[0][7] = new Lookitem(1, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/sample/lookitem/tshirts1.jpeg", "black", "tshirts", "");
        lookitem[0][8] = new Lookitem(1, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/sample/lookitem/tshirts2.jpeg", "yellow", "tshirts", "");
        lookitem[0][9] = new Lookitem(1, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/sample/lookitem/tshirts3.jpeg", "white", "tshirts", "");


        for (int i = 0; i < 10; i++) {

            lookitemRepository.save(lookitem[0][i]);
            System.out.println(lookitem[0][i].getKmId());
            System.out.println(lookitem[0][i].getLookItemCat());
        }

        return new LookitemInfo();
    }

    @PostMapping(value = "/token")
    public LoginInfo kakaoTest() {

        System.out.println("시작");

        int userId = 1;
        Member loginMember;
        loginMember = memberRepository.findByMId(userId);

        try {
            String token = jwtService.create("member", loginMember, "user");
            return new LoginInfo(token);

        }catch (Exception e){

            return new LoginInfo("fail");

        }
    }

}
