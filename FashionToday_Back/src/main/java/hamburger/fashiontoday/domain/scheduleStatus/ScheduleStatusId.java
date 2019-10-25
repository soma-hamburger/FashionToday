package hamburger.fashiontoday.domain.scheduleStatus;


import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ScheduleStatusId {

    @Id
    @EqualsAndHashCode.Include
    private int mId;

    @Id
    @EqualsAndHashCode.Include
    private String ddate;


}
