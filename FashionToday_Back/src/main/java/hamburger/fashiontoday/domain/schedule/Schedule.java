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

    @Column(name = "dcontents")
    private String dContents;

    @Column(name = "daycol")
    private String dayCol;

    @Column(name = "dcomment")
    private String dComment;

    @Column(name = "dstar")

    private int dStar;

}
