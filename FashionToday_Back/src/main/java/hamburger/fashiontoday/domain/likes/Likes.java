package hamburger.fashiontoday.domain.likes;


import hamburger.fashiontoday.domain.schedule.ScheduleId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
