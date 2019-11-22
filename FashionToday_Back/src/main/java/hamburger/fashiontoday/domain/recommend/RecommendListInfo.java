package hamburger.fashiontoday.domain.recommend;

import hamburger.fashiontoday.domain.member.Member;
import hamburger.fashiontoday.domain.schedule.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RecommendListInfo {

    private List<Requestor> requestor_array = new ArrayList<Requestor>();

    public void addSchedule(Schedule schedule, Member member) {
        requestor_array.add(new Requestor(schedule, member));
    }

    public int getSize() {
        return this.requestor_array.size();
    }


}

@Getter
@Setter
class Requestor {

    int id;
    String name;
    String self_introduction;
    String profile_image;
    RecommendSchedule schedule;
    int grade;

    public Requestor(Schedule schedule, Member member) {
        this.id = member.getMId();
        this.name = member.getMName();
        this.self_introduction = member.getMComment();
        this.profile_image = member.getMProfileUrl();
        this.schedule = new RecommendSchedule(schedule);
        this.grade = member.getMGrade();
    }
}

@Getter
@Setter
class RecommendSchedule {

    String date;
    int star_num;
    int look_num = 0;
    String title;
    String introduce;

    public RecommendSchedule(Schedule schedule) {
        this.date = schedule.getDdate();
        this.star_num = schedule.getDStar();
        this.title = schedule.getDTitle();
        this.introduce = schedule.getDIntroduce();
    }

}