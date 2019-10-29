package hamburger.fashiontoday.domain.tmplook;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TmpLook")
public class TmpLook {

    @JoinColumn(foreignKey = @ForeignKey(name = "fk_tmpLook_mid"))
    @Column(name = "mid")
    private int mId;

    @JoinColumn(foreignKey = @ForeignKey(name = "fk_tmpLook_ddate"))
    @Column(name = "ddate")
    private String ddate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tlid")
    private int tLId;

    @JoinColumn(foreignKey = @ForeignKey(name = "fk_tmpLook_kid"))
    @Column(name = "kid")
    private int kId;

    @JoinColumn(foreignKey = @ForeignKey(name = "fk_tmpLook_recommandMId"))
    @Column(name = "recommandMId")
    private int recommandMId;


}
