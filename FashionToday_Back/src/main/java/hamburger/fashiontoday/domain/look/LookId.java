package hamburger.fashiontoday.domain.look;


import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;


/**
 * @프로그램ID : HAM-PB-2005-J
 * @프로그램명 : LookId.java
 * @author : 심기성
 * @date : 2019.09.01
 * @version : 0.5
 *
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class LookId implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int mId;

    @Id
    @EqualsAndHashCode.Include
    private int ksId;

    @Id
    @EqualsAndHashCode.Include
    private int kId;

}
