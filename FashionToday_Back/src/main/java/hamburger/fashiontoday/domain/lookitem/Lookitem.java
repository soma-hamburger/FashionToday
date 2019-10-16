package hamburger.fashiontoday.domain.lookitem;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


/**
 * @author : 심기성
 * @version : 0.5
 * @프로그램ID : HAM-PB-2007-J
 * @프로그램명 : Lookitem.java
 * @date : 2019.09.01
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

    @Column(name = "color")
    private String color;

    @Column(name = "kitempicture")
    private String kItemPicture;

    @Column(name = "lookItemCat1")
    private String lookItemCat1;

    @Column(name = "lookItemCat2")
    private String lookItemCat2;

    @Column(name = "kItemColSumPicture")
    private String kItemColSumPicture;


    public Lookitem(int mId, String kItemPicture, String color, String lookItemCat1, String lookItemCat2, String kItemColSumPicture) {
        this.mId = mId;
        this.color = color;
        this.kItemPicture = kItemPicture;
        this.lookItemCat1 = lookItemCat1;
        this.lookItemCat2 = lookItemCat2;
        this.kItemColSumPicture = kItemColSumPicture;
    }

}
