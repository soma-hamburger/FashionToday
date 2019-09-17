package hamburger.fashiontoday.domain.likes;


import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @프로그램ID : HAM-PB-2002-J
 * @프로그램명 : LikesId.java
 * @author : 심기성
 * @date : 2019.09.01
 * @version : 0.5
 *
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class LikesId implements Serializable {


    @Id
    @EqualsAndHashCode.Include
    private int mId;

    @Id
    @EqualsAndHashCode.Include
    private String ddate;

    @Id
    @EqualsAndHashCode.Include
    private int liker;

}
