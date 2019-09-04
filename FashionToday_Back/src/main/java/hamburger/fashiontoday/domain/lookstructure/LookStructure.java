package hamburger.fashiontoday.domain.lookstructure;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "lookstructure")
@IdClass(LookStructureId.class)
public class LookStructure {

    @Id
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_lookitemclass_mid"))
    @Column(name = "mid")
    private int mid;

    @Id
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_lookitemclass_kmid"))
    @Column(name = "kmid")
    private int kmId;

    @Id
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_lookitemclass_ksid"))
    @Column(name = "ksid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ksId;


}
