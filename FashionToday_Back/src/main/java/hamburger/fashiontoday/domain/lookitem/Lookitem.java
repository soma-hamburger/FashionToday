package hamburger.fashiontoday.domain.lookitem;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


/**
 * @프로그램ID : HAM-PB-2007-J
 * @프로그램명 : Lookitem.java
 * @author : 심기성
 * @date : 2019.09.01
 * @version : 0.5
 *
 */
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
    private int mId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "kmid")
    private int kmId;

    @JoinColumn(foreignKey = @ForeignKey(name = "fk_lookitem_kcid"))
    @Column(name = "kcid")
    private int kcId;

    @Column(name = "kitempicture")
    private String kItemPicture;

    @Column(name = "lookItemCol")
    private String lookItemCol;

    @Column(name = "kItemColSumPicture")
    private String kItemColSumPicture;


    public Lookitem(int mId, int kcId, String kItemPicture, String lookItemCol, String kItemColSumPicture) {
        this.mId = mId;
        this.kcId = kcId;
        this.kItemPicture = kItemPicture;
        this.lookItemCol = lookItemCol;
        this.kItemColSumPicture = kItemColSumPicture;
    }

}
