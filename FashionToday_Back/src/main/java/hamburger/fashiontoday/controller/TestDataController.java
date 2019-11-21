package hamburger.fashiontoday.controller;


import hamburger.fashiontoday.domain.lookitem.Lookitem;
import hamburger.fashiontoday.domain.lookitem.LookitemInfo;
import hamburger.fashiontoday.domain.lookitem.LookitemRepository;
import hamburger.fashiontoday.domain.member.LoginInfo;
import hamburger.fashiontoday.domain.member.Member;
import hamburger.fashiontoday.domain.member.MemberInfo;
import hamburger.fashiontoday.domain.member.MemberRepository;
import hamburger.fashiontoday.domain.recommend.RecommendListInfo;
import hamburger.fashiontoday.domain.schedule.Schedule;
import hamburger.fashiontoday.domain.schedule.ScheduleInfo;
import hamburger.fashiontoday.domain.schedule.ScheduleRepository;
import hamburger.fashiontoday.domain.scheduleStatus.ScheduleStatus;
import hamburger.fashiontoday.domain.scheduleStatus.ScheduleStatusRepository;
import hamburger.fashiontoday.domain.tmplook.TmpLook;
import hamburger.fashiontoday.domain.tmplook.TmpLookInfo;
import hamburger.fashiontoday.domain.tmplook.TmpLookRepository;
import hamburger.fashiontoday.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


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

    @Autowired
    TmpLookRepository tmpLookRepository;


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

        // 아이유 옷
        lookitem[0][0] = new Lookitem(7, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/look_1/bag1.png", "green", "bag", "");
        lookitem[0][1] = new Lookitem(7, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/look_1/pants1.png", "blue", "pants", "");
        lookitem[0][2] = new Lookitem(7, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/look_1/shoes1.png", "black", "shoes", "");
        lookitem[0][3] = new Lookitem(7, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/look_1/tee1.png", "green", "tee", "");
        lookitem[0][4] = new Lookitem(7, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/look_1/tee2.png", "brown", "tee", "");

        // 나연 옷
        lookitem[1][0] = new Lookitem(9, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/look_2/acc1.png", "gole", "accessory", "");
        lookitem[1][1] = new Lookitem(9, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/look_2/acc2.png", "gold", "accessory", "");
        lookitem[1][2] = new Lookitem(9, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/look_2/acc3.png", "gold", "accessory", "");
        lookitem[1][3] = new Lookitem(9, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/look_2/one.png", "green", "dress", "");
        lookitem[1][4] = new Lookitem(9, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/look_2/shoes.png", "green", "shoes", "");


        // 동훈 옷
        lookitem[2][0] = new Lookitem(1156764820, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/look_5/coat1.png", "brown", "dress", "");
        lookitem[2][1] = new Lookitem(1156764820, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/look_5/hood1.png", "black", "tee", "");
        lookitem[2][2] = new Lookitem(1156764820, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/look_5/jeans1.png", "blue", "jean", "");
        lookitem[2][3] = new Lookitem(1156764820, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/look_5/pants1.png", "brown", "jena", "");
        lookitem[2][4] = new Lookitem(1156764820, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/look_5/pants2.png", "yellow", "jean", "");
        lookitem[2][5] = new Lookitem(1156764820, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/look_5/shirts1.png", "white", "shirt", "");
        lookitem[2][6] = new Lookitem(1156764820, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/look_5/shoes1.png", "white", "shoes", "");
        lookitem[2][7] = new Lookitem(1156764820, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/look_5/tee1.png", "black", "tee", "");
        lookitem[2][8] = new Lookitem(1156764820, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/look_5/tee2.png", "yellow", "tee", "");
        lookitem[2][9] = new Lookitem(1156764820, "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/look_5/tee3.png", "white", "tee", "");

        for (int i = 0; i < 10; i++) {
            lookitemRepository.save(lookitem[2][i]);
        }

        return new LookitemInfo();
    }

    @PostMapping(value = "/token")
    public LoginInfo testToken() {

        System.out.println("시작");

        int userId = 7;
        Member loginMember;
        loginMember = memberRepository.findByMId(userId);

        try {
            String token = jwtService.create("member", loginMember, "user");
            return new LoginInfo(token);

        }catch (Exception e){

            return new LoginInfo("fail");

        }
    }

    @PostMapping(value = "/schedule")
    public ScheduleInfo kakaoTest() {

        Schedule[][] schedule = new Schedule[10][10];

        // 1번 유저 스케줄
        schedule[0][0] = new Schedule(1,"20191105","생일 입니다.","여자친구와 생일 데이트 하는 룩이 필요해요",1);
        schedule[0][1] = new Schedule(1,"20191113","동기 모임","오랜만에 만나는 동기들에게 달라진 모습을 보여주고 싶어요.",2);
        schedule[0][2] = new Schedule(1,"20191117","영상 촬영","영상을 촬영하는 날입니다. 신뢰감을 주고 싶어요",3);
        schedule[0][3] = new Schedule(1,"20191120","최종 발표","발표용 깔끔한 룩이 필요합니다.",4);


        //2번 유저 스케줄
        schedule[1][0] = new Schedule(7,"20191105","파티 입니다.","파티 피플로 거듭나고 싶어요",1);
        schedule[1][1] = new Schedule(7,"20191113","음악 감상","잔잔한 음악과 함께",2);
        schedule[1][2] = new Schedule(7,"20191117","가을 분위기","가을은 언제나 외롭죠 외롭지 않게 보이고 싶어요",3);
        schedule[1][3] = new Schedule(7,"20191108","사랑하는 사람과","제 남자친구에게 잘 보이고 싶어요.",4);

        // 3번 유저 스케줄
        schedule[2][0] = new Schedule(9,"20191105","소개팅 나가는 날","소개팅 나가는 날이에요. 잘보이고 싶어요",1);
        schedule[2][1] = new Schedule(9,"20191113","가을 나들이","가을인데 나들이 갈까요?.",2);
        schedule[2][2] = new Schedule(9,"20191117","너무 추워요","영상을 촬영하는 날입니다. 신뢰감을 주고 싶어요",3);
        schedule[2][3] = new Schedule(9,"20191118","부모님 보러 가는 날","부모님 만날때 깔끔한 룩이 필요합니다.",4);

        // 4번 유저 스케줄
        schedule[3][0] = new Schedule(5,"20191105","패션쇼","여자친구와 생일 데이트 하는 룩이 필요해요",1);
        schedule[3][1] = new Schedule(5,"20191113","과학사 모임","과학자처럼 보이기 싫어요.",2);
        schedule[3][2] = new Schedule(5,"20191117","종강 파티","우리 파티 하러 갈까요?? 종강?",3);
        schedule[3][3] = new Schedule(5,"20191110","친구 생일","친구보다 잘나보이고 싶어요",4);



        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                scheduleRepository.save(schedule[i][j]);
            }
        }
        return new ScheduleInfo();
    }



    //
    @GetMapping(value = "/recommendList")
    public RecommendListInfo scheduleList() {
        System.out.println("추천 컨트롤러");

        int loginMemberId = 0;
        int scheduleListchecker = 0;
        String nowDate = new String();
        LocalDateTime localDateTime = LocalDateTime.now();
        if (localDateTime.getMonthValue() < 10) {
            nowDate = String.valueOf(localDateTime.getYear()) + String.valueOf(localDateTime.getMonthValue()) + String.valueOf(localDateTime.getDayOfMonth());
        } else {
            nowDate = String.valueOf(localDateTime.getYear()) + String.valueOf(localDateTime.getMonthValue()) + String.valueOf(localDateTime.getDayOfMonth());
        }
        RecommendListInfo recommendListInfo = new RecommendListInfo();

//        // 로그인 여부 확인
//        if (jwtService.isUsable(token)) {
//            loginMemberId = jwtService.getMember(token);
//            System.out.println("유저 아이디 : " + loginMemberId);
//
//        } else {
//            return recommendListInfo;
//        }

        // 동훈이로 로그
        loginMemberId = 1158775578;

        List<TmpLook> tmpLooks = tmpLookRepository.findByrecommandMId(loginMemberId);
        List<ScheduleStatus> scheduleStatusList = scheduleStatusRepository.findByMIdNotAndLeftNotOrderByLeftDesc(loginMemberId, 0);

        while (recommendListInfo.getSize() < 5 && scheduleListchecker < scheduleStatusList.size()) {
            System.out.println(scheduleListchecker+" : 번째 돕니다.");
            ScheduleStatus nowScheduleStatus = scheduleStatusList.get(scheduleListchecker);
            boolean isRecommand = false;
            for (int i = 0; i < tmpLooks.size(); i++) {
                TmpLook nowTmpLook = tmpLooks.get(i);
                if (nowTmpLook.getDdate().equals(nowScheduleStatus.getDdate())&&nowTmpLook.getMId()==nowScheduleStatus.getMId()) {
                    if(nowTmpLook.getRecommandMId()==loginMemberId) {
                        isRecommand = true;
                        break;
                    }
                }
            }
            if (!isRecommand) {
                Member scheduleMember = memberRepository.findByMId(nowScheduleStatus.getMId());
                recommendListInfo.addSchedule(scheduleRepository.findByMIdAndDdate(nowScheduleStatus.getMId(), nowScheduleStatus.getDdate()), scheduleMember);
            }
            scheduleListchecker++;
        }


        return recommendListInfo;
    }


    @PostMapping(value = "/tmpLook")
    TmpLookInfo saveTmpLook(){

        TmpLook[] tmpLook = new TmpLook[100];

        tmpLook[0] = new TmpLook(1, "20191105", "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/t_look/tlook1.jpeg", "생일엔 멋스럽게", "생일이라 그 누구보다 빛나는 룩을 추천드렸어요 ㅎㅎ.", memberRepository.findByMId(7));
        tmpLook[1] = new TmpLook(1, "20191113", "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/t_look/tlook2.jpeg", "보여줄게 달라진 나", "달라진 나를 보여주세요.", memberRepository.findByMId(1));
        tmpLook[2] = new TmpLook(1, "20191117", "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/t_look/tlook3.jpeg", "카메라 앞에 빛이나네", "카메라 앞 셋팅 완료입니다.", memberRepository.findByMId(6));
        tmpLook[3] = new TmpLook(1, "20191120", "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/t_look/tlook4.jpeg", "자신감 뿜뿜", "기빗투유 마마마마마~ 눈빛", memberRepository.findByMId(6));
        tmpLook[4] = new TmpLook(5, "20191105", "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/t_look/tlook5.jpeg", "하트 뿅뿅", "뿅뿅 날아가는 사랑의 총알 ", memberRepository.findByMId(3));
        tmpLook[5] = new TmpLook(5, "20191110", "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/t_look/tlook6.jpeg", "내가 제일 잘나가", "2ne1이 부릅니다.", memberRepository.findByMId(4));
        tmpLook[6] = new TmpLook(5, "20191113", "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/t_look/tlook7.jpeg", "문학적 느낌", "더이상의 이과는 없다. 문과의 시대가 도래한다.", memberRepository.findByMId(2));
        tmpLook[7] = new TmpLook(5, "20191117", "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/t_look/tlook8.jpeg", "에블바리 셔플린", "레트로 셔플 그 자", memberRepository.findByMId(1));
        tmpLook[8] = new TmpLook(7, "20191105", "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/t_look/tlook9.jpeg", "하트뿅뿅 2", "제가 먼저 반할 것 같아", memberRepository.findByMId(8));
        tmpLook[9] = new TmpLook(7, "20191108", "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/t_look/tlook10.jpeg", "룩을 듣다", "록유어 바", memberRepository.findByMId(8));
        tmpLook[10] = new TmpLook(7, "20191113", "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/t_look/tlook11.jpeg", "겨울이 더 외로워", "그러니 가을은 이렇", memberRepository.findByMId(7));
        tmpLook[11] = new TmpLook(7, "20191117", "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/t_look/tlook12.jpeg", "썸 탈거야", "썸타고 싶다. 나두.", memberRepository.findByMId(5));
        tmpLook[12] = new TmpLook(9, "20191105", "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/t_look/tlook13.jpeg", "좋은 사람!", "좋은 사람으로 보일거에", memberRepository.findByMId(4));
        tmpLook[13] = new TmpLook(9, "20191113", "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/t_look/tlook14.jpeg", "가을 나들이", "그래도 나들이는 봄이 최곱니다.", memberRepository.findByMId(2));
        tmpLook[14] = new TmpLook(9, "20191117", "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/t_look/tlook15.jpeg", "신대감 신뢰감", "옛날 신대감이 주었던 신뢰감 그느낌 그대로 주는 ", memberRepository.findByMId(3));
        tmpLook[15] = new TmpLook(9, "20191118", "https://s3.ap-northeast-2.amazonaws.com/data.pashiontoday.com/test_data/t_look/tlook16.jpeg", "엄빠 사랑해요~~", "우리가 있자나요~~", memberRepository.findByMId(4));

        for(int i = 0; i<16;i++){
            tmpLookRepository.save(tmpLook[i]);
        }

        return new TmpLookInfo();
    }


}
