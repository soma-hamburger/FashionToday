package hamburger.fashiontoday.domain.schedule;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


/**
 * @프로그램ID : HAM-PB-2017-J
 * @프로그램명 : ScheduleId.java
 * @author : 심기성
 * @date : 2019.09.01
 * @version : 0.5
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Schedule")
@IdClass(ScheduleId.class)
public class Schedule {

    @Id
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_schdule_mid"))
    @Column(name = "mid")
    private int mId;

    @Id
    @Column(name = "ddate")
    private String ddate;

    @JoinColumn(foreignKey = @ForeignKey(name = "fk_schdule_ksid"))
    @Column(name = "kid")
    private int kId;

    @Column(name = "dtitle")
    private String dTitle;

    @Column(name = "dintorduce")
    private String dIntroduce;

    @Column(name = "dstar")
    private int dStar;

    @Column(name = "dyear")
    private String dYear;

    @Column(name = "dmonth")
    private String dMonth;


    public Schedule(int mId, String ddate, String dTitle, String dIntroduce, int dStar) {
        this.mId = mId;
        this.ddate = ddate;
        this.dTitle = dTitle;
        this.dIntroduce = dIntroduce;
        this.dStar = dStar;
    }
}
