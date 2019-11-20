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
        schedule[0][0] = new Schedule(1,"20191105","생일 입니다.","여자친구와 생일 데이트 하는 룩이 필요해요",5);
        schedule[0][1] = new Schedule(1,"20191113","동기 모임","오랜만에 만나는 동기들에게 달라진 모습을 보여주고 싶어요.",2);
        schedule[0][2] = new Schedule(1,"20191117","영상 촬영","영상을 촬영하는 날입니다. 신뢰감을 주고 싶어요",2);
        schedule[0][3] = new Schedule(1,"20191122","최종 발표","발표용 깔끔한 룩이 필요합니다.",1);
        schedule[0][4] = new Schedule(1,"20191124","유럽여행 시작","유럽 여행을 떠납니다.",5);
        schedule[0][5] = new Schedule(1,"20191129","여자친구와 100일","여자친구와 100일입니다. 특별한 룩이 필요합니다.",5);


        //2번 유저 스케줄
        schedule[1][0] = new Schedule(2,"20191105","생일 입니다.","여자친구와 생일 데이트 하는 룩이 필요해요",5);
        schedule[1][1] = new Schedule(2,"20191113","동기 모임","오랜만에 만나는 동기들에게 달라진 모습을 보여주고 싶어요.",2);
        schedule[1][2] = new Schedule(2,"20191117","영상 촬영","영상을 촬영하는 날입니다. 신뢰감을 주고 싶어요",2);
        schedule[1][3] = new Schedule(2,"20191122","최종 발표","발표용 깔끔한 룩이 필요합니다.",1);
        schedule[1][4] = new Schedule(2,"20191124","유럽여행 시작","유럽 여행을 떠납니다.",5);
        schedule[1][5] = new Schedule(2,"20191129","여자친구와 100일","여자친구와 100일입니다. 특별한 룩이 필요합니다.",5);

        // 3번 유저 스케줄
        schedule[2][0] = new Schedule(3,"20191105","생일 입니다.","여자친구와 생일 데이트 하는 룩이 필요해요",5);
        schedule[2][1] = new Schedule(3,"20191113","동기 모임","오랜만에 만나는 동기들에게 달라진 모습을 보여주고 싶어요.",2);
        schedule[2][2] = new Schedule(3,"20191117","영상 촬영","영상을 촬영하는 날입니다. 신뢰감을 주고 싶어요",2);
        schedule[2][3] = new Schedule(3,"20191122","최종 발표","발표용 깔끔한 룩이 필요합니다.",1);
        schedule[2][4] = new Schedule(3,"20191124","유럽여행 시작","유럽 여행을 떠납니다.",5);
        schedule[2][5] = new Schedule(3,"20191129","여자친구와 100일","여자친구와 100일입니다. 특별한 룩이 필요합니다.",5);

        // 4번 유저 스케줄
        schedule[3][0] = new Schedule(4,"20191105","생일 입니다.","여자친구와 생일 데이트 하는 룩이 필요해요",5);
        schedule[3][1] = new Schedule(4,"20191113","동기 모임","오랜만에 만나는 동기들에게 달라진 모습을 보여주고 싶어요.",2);
        schedule[3][2] = new Schedule(4,"20191117","영상 촬영","영상을 촬영하는 날입니다. 신뢰감을 주고 싶어요",2);
        schedule[3][3] = new Schedule(4,"20191122","최종 발표","발표용 깔끔한 룩이 필요합니다.",1);
        schedule[3][4] = new Schedule(4,"20191124","유럽여행 시작","유럽 여행을 떠납니다.",5);
        schedule[3][5] = new Schedule(4,"20191129","여자친구와 100일","여자친구와 100일입니다. 특별한 룩이 필요합니다.",5);

        // 5번 유저 스케줄
        schedule[4][0] = new Schedule(5,"20191105","생일 입니다.","여자친구와 생일 데이트 하는 룩이 필요해요",5);
        schedule[4][1] = new Schedule(5,"20191113","동기 모임","오랜만에 만나는 동기들에게 달라진 모습을 보여주고 싶어요.",2);
        schedule[4][2] = new Schedule(5,"20191117","영상 촬영","영상을 촬영하는 날입니다. 신뢰감을 주고 싶어요",2);
        schedule[4][3] = new Schedule(5,"20191122","최종 발표","발표용 깔끔한 룩이 필요합니다.",1);
        schedule[4][4] = new Schedule(5,"20191124","유럽여행 시작","유럽 여행을 떠납니다.",5);
        schedule[4][5] = new Schedule(5,"20191129","여자친구와 100일","여자친구와 100일입니다. 특별한 룩이 필요합니다.",5);

        // 6번 유저 스케줄
        schedule[5][0] = new Schedule(6,"20191105","생일 입니다.","여자친구와 생일 데이트 하는 룩이 필요해요",5);
        schedule[5][1] = new Schedule(6,"20191113","동기 모임","오랜만에 만나는 동기들에게 달라진 모습을 보여주고 싶어요.",2);
        schedule[5][2] = new Schedule(6,"20191117","영상 촬영","영상을 촬영하는 날입니다. 신뢰감을 주고 싶어요",2);
        schedule[5][3] = new Schedule(6,"20191122","최종 발표","발표용 깔끔한 룩이 필요합니다.",1);
        schedule[5][4] = new Schedule(6,"20191124","유럽여행 시작","유럽 여행을 떠납니다.",5);
        schedule[5][5] = new Schedule(6,"20191129","여자친구와 100일","여자친구와 100일입니다. 특별한 룩이 필요합니다.",5);

        

        // 7번 유저 스케줄
        schedule[6][0] = new Schedule(7,"20191105","생일 입니다.","여자친구와 생일 데이트 하는 룩이 필요해요",5);
        schedule[6][1] = new Schedule(7,"20191113","동기 모임","오랜만에 만나는 동기들에게 달라진 모습을 보여주고 싶어요.",2);
        schedule[6][2] = new Schedule(7,"20191117","영상 촬영","영상을 촬영하는 날입니다. 신뢰감을 주고 싶어요",2);
        schedule[6][3] = new Schedule(7,"20191122","최종 발표","발표용 깔끔한 룩이 필요합니다.",1);
        schedule[6][4] = new Schedule(7,"20191124","유럽여행 시작","유럽 여행을 떠납니다.",5);
        schedule[6][5] = new Schedule(7,"20191129","여자친구와 100일","여자친구와 100일입니다. 특별한 룩이 필요합니다.",5);





        for (int i = 0; i < 6; i++) {
            scheduleRepository.save(schedule[0][i]);
            scheduleStatusRepository.save(new ScheduleStatus(schedule[0][i]));
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
            nowDate = String.valueOf(localDateTime.getYear()) + String.valueOf(localDateTime.getMonth()) + String.valueOf(localDateTime.getDayOfMonth());
        } else {
            nowDate = String.valueOf(localDateTime.getYear()) + String.valueOf(localDateTime.getMonth()) + String.valueOf(localDateTime.getDayOfMonth());
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



}
