package hamburger.fashiontoday.domain.scheduleStatus;

import hamburger.fashiontoday.domain.schedule.Schedule;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ScheduleStatus")
public class ScheduleStatus {

    @JoinColumn(foreignKey = @ForeignKey(name = "fk_schduleStatus_mid"))
    @Column(name = "mid")
    private int mId;

    @JoinColumn(foreignKey = @ForeignKey(name = "fk_schduleStatus_ddate"))
    @Column(name = "ddate")
    private String ddate;

    @Id
    @Column(name = "ssid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ssid;

    // 1 -> 퍼플 스타
    // 2 -> 일반 스타
    @Column(name = "sPurple")
    private int purple = 2;

    @Column(name = "sleft")
    private int left;

    public ScheduleStatus(Schedule schedule){
        this.mId = schedule.getMId();
        this.ddate = schedule.getDdate();
        this.left = schedule.getDStar();
    }

    public void addTmpLook(){
        this.left--;
    }

    public void purple(){
        this.purple = 1;
    }

    public void normal(){
        this.purple = 2;
    }

}
