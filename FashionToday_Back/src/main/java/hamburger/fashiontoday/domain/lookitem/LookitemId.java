package hamburger.fashiontoday.domain.lookitem;


import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class LookitemId implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int mid;

    @Id
    @EqualsAndHashCode.Include
    private int kmId;

}
