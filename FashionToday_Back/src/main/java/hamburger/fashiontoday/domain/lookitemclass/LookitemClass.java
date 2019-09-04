package hamburger.fashiontoday.domain.lookitemclass;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "lookitemclass")
@NoArgsConstructor
public class LookitemClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "kcid")
    private int kcId;

    @Column(name = "kitemclasscol1")
    private String kItemClassCol1;

    @Column(name = "kitemclasscol2")
    private String kItemClassCol2;

    @Column(name = "kitemclassval1")
    private String kItemClassVal1;

    @Column(name = "kitemclassval2")
    private String kItemClassVal2;


    public LookitemClass(String kItemClassCol1, String kItemClassCol2, String kItemClassVal1, String kItemClassVal2) {
        this.kItemClassCol1 = kItemClassCol1;
        this.kItemClassCol2 = kItemClassCol2;
        this.kItemClassVal1 = kItemClassVal1;
        this.kItemClassVal2 = kItemClassVal2;
    }
}
