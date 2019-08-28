package hamburger.fashiontoday.domain.lookitem;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "lookitem")
public class Lookitem {

    @Id
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_lookitem_mid"))
    @Column(name = "mid")
    private long mid;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "kmid")
    private long kmId;

    @JoinColumn(foreignKey = @ForeignKey(name = "fk_lookitem_kcid"))
    @Column(name = "kcid")
    private long kcid;


    @Column(name = "kitempicture")
    private String kItemPicture;

    @Column(name = "lookItemCol")
    private String lookItemCol;

    @Column(name = "lookItemCol")
    private String kItemColSumPicture;

}
