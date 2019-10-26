package hamburger.fashiontoday.domain.scheduleStatus;


import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ScheduleStatusId implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int mId;

    @Id
    @EqualsAndHashCode.Include
    private String ddate;


}
