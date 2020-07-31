package hamburger.fashiontoday.domain.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ScheduleListInfo {

    String remark;
    int user_id;
    int schedule_num;
    List<ScheduleArray> schedule_array = new ArrayList<ScheduleArray>();


    public void addScheduleList(List<Schedule> scheduleList) {
        for(int i = 0; i<scheduleList.size();i++){
            schedule_array.add(new ScheduleArray(scheduleList.get(i)));
        }
    }
}

@Getter
@Setter
class ScheduleArray{

    String date;

    public ScheduleArray(Schedule schedule){
        this.date = schedule.getDdate();
    }

}
