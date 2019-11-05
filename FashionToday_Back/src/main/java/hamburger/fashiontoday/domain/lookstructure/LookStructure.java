package hamburger.fashiontoday.domain.lookstructure;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @프로그램ID : HAM-PB-2012-J
 * @프로그램명 : LookStructure.java
 * @author : 심기성
 * @date : 2019.09.01
 * @version : 0.5
 *
 */
@Entity
@Getter
@Setter
@Table(name = "lookstructure")
@IdClass(LookStructureId.class)
@NoArgsConstructor
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
    @Column(name = "tlid")
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_lookitemclass_tlid"))
    private int ksId;


    public LookStructure(int mid, int kmId, int ksId) {
        this.mid = mid;
        this.kmId = kmId;
        this.ksId = ksId;
    }
}
