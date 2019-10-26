package hamburger.fashiontoday.domain.lookitem;


import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @프로그램ID : HAM-PB-2008-J
 * @프로그램명 : LookitemId.java
 * @author : 심기성
 * @date : 2019.09.01
 * @version : 0.5
 *
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class LookitemId implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int mId;

    @Id
    @EqualsAndHashCode.Include
    private int kmId;

}
