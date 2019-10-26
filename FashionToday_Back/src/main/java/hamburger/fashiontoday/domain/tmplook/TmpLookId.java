package hamburger.fashiontoday.domain.tmplook;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class TmpLookId {

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
