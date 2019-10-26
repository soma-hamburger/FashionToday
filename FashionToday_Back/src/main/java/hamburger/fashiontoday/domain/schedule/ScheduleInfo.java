package hamburger.fashiontoday.domain.schedule;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleInfo {

    private String remark = "fail";

    private int mId;
    private String date;
    private String title;
    private String introduce;
    private int star;

    public ScheduleInfo(String error){
        this.remark = error;
    }

    public ScheduleInfo(Schedule schedule) {
        remark = "success";
        this.mId = schedule.getMId();
        this.date = schedule.getDdate();
        this.title = schedule.getDTitle();
        this.introduce = schedule.getDIntroduce();
        this.star = schedule.getDStar();
    }

}
