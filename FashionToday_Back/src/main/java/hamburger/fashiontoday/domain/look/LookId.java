package hamburger.fashiontoday.domain.look;


import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class LookId implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int mId;

    @Id
    @EqualsAndHashCode.Include
    private int kmId;

    @Id
    @EqualsAndHashCode.Include
    private int kId;

}
