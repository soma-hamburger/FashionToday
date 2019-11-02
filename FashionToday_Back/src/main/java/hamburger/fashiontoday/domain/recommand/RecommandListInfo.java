package hamburger.fashiontoday.domain.recommand;

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
public class RecommandListInfo {

    private List<Requestor> request_array = new ArrayList<Requestor>();

    public void addSchedule(Schedule schedule, Member member) {
        request_array.add(new Requestor(schedule, member));
    }

    public int getSize() {
        return this.request_array.size();
    }


}

@Getter
@Setter
class Requestor {

    int id;
    String name;
    String self_introduction;
    String profile_image;
    RecommandSchedule schedule;

    public Requestor(Schedule schedule, Member member) {
        this.id = member.getMId();
        this.name = member.getMName();
        this.self_introduction = member.getMComment();
        this.profile_image = member.getMProfileUrl();
        this.schedule = new RecommandSchedule(schedule);
    }
}

@Getter
@Setter
class RecommandSchedule {

    String date;
    int star_num;
    int look_num = 0;
    String schedule_title;
    String schedule_introduce;

    public RecommandSchedule(Schedule schedule) {
        this.date = schedule.getDdate();
        this.star_num = schedule.getDStar();
        this.schedule_title = schedule.getDTitle();
        this.schedule_introduce = schedule.getDIntroduce();
    }

}