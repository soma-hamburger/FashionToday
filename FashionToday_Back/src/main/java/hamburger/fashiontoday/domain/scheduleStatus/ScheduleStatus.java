package hamburger.fashiontoday.domain.scheduleStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ScheduleStatus")
@IdClass(ScheduleStatusId.class)
public class ScheduleStatus {

    @Id
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_schduleStatus_mid"))
    @Column(name = "mid")
    private int mId;

    @Id
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
    private int purple;

    @Column(name = "sleft")
    private int left;

    public ScheduleStatus(int mId, String ddate, int left) {
        this.mId = mId;
        this.ddate = ddate;
        this.left = left;
    }

    public void purple(){
        this.purple = 1;
    }

    public void normal(){
        this.purple = 2;
    }

}
