package hamburger.fashiontoday.domain.schedule;


import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @프로그램ID : HAM-PB-2018-J
 * @프로그램명 : ScheduleId.java
 * @author : 심기성
 * @date : 2019.09.01
 * @version : 0.5
 *
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ScheduleId implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int mId;

    @Id
    @EqualsAndHashCode.Include
    private String ddate;

}
