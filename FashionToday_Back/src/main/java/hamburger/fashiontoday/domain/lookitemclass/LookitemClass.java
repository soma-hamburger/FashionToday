package hamburger.fashiontoday.domain.lookitemclass;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "lookitem")
public class LookitemClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "kcid")
    private long kcId;

    @Column(name = "kitemclasscol1")
    private String kItemClassCol1;

    @Column(name = "kitemclasscol2")
    private String kItemClassCol2;

    @Column(name = "kitemclassval1")
    private String kItemClassVal1;

    @Column(name = "kitemclassval2")
    private String kItemClassVal2;

}
