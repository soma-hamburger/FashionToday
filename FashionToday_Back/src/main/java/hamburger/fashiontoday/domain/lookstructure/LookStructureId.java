package hamburger.fashiontoday.domain.lookstructure;


import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @프로그램ID : HAM-PB-2013-J
 * @프로그램명 : LookStructureId.java
 * @author : 심기성
 * @date : 2019.09.01
 * @version : 0.5
 *
 */
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
    private int ksId;

}
