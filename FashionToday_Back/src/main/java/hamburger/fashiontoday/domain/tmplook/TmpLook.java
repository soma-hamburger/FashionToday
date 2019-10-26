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
@IdClass(TmpLookId.class)
public class TmpLook {

    @Id
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_tmpLook_mid"))
    @Column(name = "mid")
    private int mId;

    @Id
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_tmpLook_ddate"))
    @Column(name = "ddate")
    private String ddate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tlid")
    private int tLId;

    @Id
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_tmpLook_kid"))
    @Column(name = "kid")
    private int kId;




}
