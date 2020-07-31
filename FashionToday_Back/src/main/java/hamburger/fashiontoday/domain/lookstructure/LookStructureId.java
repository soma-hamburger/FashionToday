package hamburger.fashiontoday.domain.lookstructure;


import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class LookStructureId implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int mid;


    @Id
    @EqualsAndHashCode.Include
    private int kmId;

    @Id
    @EqualsAndHashCode.Include
    private int tlId;

}
