package hamburger.fashiontoday.domain.likes;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


/**
 * @프로그램ID : HAM-PB-2001-J
 * @프로그램명 : Likes.java
 * @author : 심기성
 * @date : 2019.09.01
 * @version : 0.5
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "likes")
@IdClass(LikesId.class)
public class Likes {

    @Id
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_likes_mid"))
    @Column(name = "mid")
    private int mId;

    @Id
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_likes_ddate"))
    @Column(name = "ddate")
    private String ddate;

    @Id
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_likes_liker"))
    @Column(name = "liker")
    private int liker;

}
