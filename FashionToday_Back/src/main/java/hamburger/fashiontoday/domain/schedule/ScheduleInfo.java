package hamburger.fashiontoday.domain.schedule;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleInfo {

    private String remark = "fail";

    private int user_id;
    private String date;
    private String title;
    private String introduce;
    private int star;

    public ScheduleInfo(String error){
        this.remark = error;
    }

    public ScheduleInfo(Schedule schedule) {
        remark = "success";
        this.user_id = schedule.getMId();
        this.date = schedule.getDdate();
        this.title = schedule.getDTitle();
        this.introduce = schedule.getDIntroduce();
        this.star = schedule.getDStar();
    }

}
