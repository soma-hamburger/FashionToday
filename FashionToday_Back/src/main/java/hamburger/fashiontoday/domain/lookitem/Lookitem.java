package hamburger.fashiontoday.domain.lookitem;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "lookitem")
@IdClass(LookitemId.class)
@NoArgsConstructor
public class Lookitem {

    @Id
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_lookitem_mid"))
    @Column(name = "mid")
    private int mid;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "kmid")
    private int kmId;

    @JoinColumn(foreignKey = @ForeignKey(name = "fk_lookitem_kcid"))
    @Column(name = "kcid")
    private int kcid;

    @Column(name = "kitempicture")
    private String kItemPicture;

    @Column(name = "lookItemCol")
    private String lookItemCol;

    @Column(name = "kItemColSumPicture")
    private String kItemColSumPicture;


    public Lookitem(int mid, int kcid, String kItemPicture, String lookItemCol, String kItemColSumPicture) {
        this.mid = mid;
        this.kcid = kcid;
        this.kItemPicture = kItemPicture;
        this.lookItemCol = lookItemCol;
        this.kItemColSumPicture = kItemColSumPicture;
    }

}
