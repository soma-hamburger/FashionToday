package hamburger.fashiontoday.domain.look;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "look")
@IdClass(LookId.class)
public class Look {

    @Id
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_look_mid"))
    @Column(name = "mid")
    private int mId;

    @Id
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_look_ksid"))
    @Column(name = "ksid")
    private int ksId;

    @Id
    @Column(name = "kid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int kId;

    @JoinColumn(foreignKey = @ForeignKey(name = "fk_look_createrId"))
    @Column(name = "createrId")
    private int createrId;

    @Column(name = "lookTitle")
    private String lookTitle;

    @Column(name = "lookDesc")
    private String lookDesc;

    @Column(name = "lookTemperature")
    private String kTemperature;

    @Column(name = "kweather")
    private String kWeather;

    @Column(name = "kdatetime")
    private String kDateTime;

    @Column(name = "kdate")
    private String kDate;

    @Column(name = "ktime")
    private String kTime;

}
