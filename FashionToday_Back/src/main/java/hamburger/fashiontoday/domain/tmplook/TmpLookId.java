package hamburger.fashiontoday.domain.tmplook;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class TmpLookId implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int mId;

    @Id
    @EqualsAndHashCode.Include
    private String ddate;

    @Id
    @EqualsAndHashCode.Include
    private int tLId;

}
