package hamburger.fashiontoday.domain.look;


import hamburger.fashiontoday.domain.tmplook.TmpLook;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;


/**
 * @프로그램ID : HAM-PB-2004-J
 * @프로그램명 : Look.java
 * @author : 심기성
 * @date : 2019.09.01
 * @version : 0.5
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "look")
public class Look {

    @Id
    @Column(name = "kid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int kId;

    @JoinColumn(foreignKey = @ForeignKey(name = "fk_look_mid"))
    @Column(name = "mid")
    private int mId;

    @Column(name = "tlid")
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_look_tlid"))
    private int tlid;

    @Column(name = "lookTemperature")
    private String kTemperature;

    @Column(name = "kweather")
    private String kWeather;

    @Column(name = "kshare")
    @ColumnDefault("0")
    private int kShare;


    public Look(TmpLook tmpLook) {
        this.mId = tmpLook.getMId();
        this.tlid = tmpLook.getTLId();
        this.kTemperature = "17";
        this.kWeather = "맑음";
    }


}
