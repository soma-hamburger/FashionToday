package hamburger.fashiontoday.domain.Recommand;

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

    private List<Schedule> scheduleList = new ArrayList<Schedule>();

    public void addSchedule(Schedule schedule){
        scheduleList.add(schedule);
    }

    public int getSize() {
        return this.scheduleList.size();
    }
}
