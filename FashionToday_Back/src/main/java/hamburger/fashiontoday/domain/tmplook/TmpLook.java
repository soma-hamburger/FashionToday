package hamburger.fashiontoday.domain.tmplook;


import hamburger.fashiontoday.domain.member.Member;
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

    @Column(name = "tlUrl")
    private String tlUrl;

    @Column(name = "tTitle")
    private String tTitle;

    @Column(name = "tIntroduce")
    private String tIntroduce;


    @JoinColumn(foreignKey = @ForeignKey(name = "fk_tmpLook_recommandMId"))
    @Column(name = "recommandMId")
    private int recommandMId;

    @JoinColumn(foreignKey = @ForeignKey(name = "fk_tmpLook_recommanderImg"))
    @Column(name = "recommanderImg")
    private String recommanderImg;

    @JoinColumn(foreignKey = @ForeignKey(name = "fk_tmpLook_recommanderName"))
    @Column(name = "recommanderName")
    private String recommanderName;

    @JoinColumn(foreignKey = @ForeignKey(name = "fk_tmpLook_recommanderGrade"))
    @Column(name = "recommanderGrade")
    private int recommanderGrade;

    public TmpLook(int mId, String ddate, String tlUrl, String tTitle, String tIntroduce, Member recommender) {
        this.mId = mId;
        this.ddate = ddate;
        this.tlUrl = tlUrl;
        this.recommandMId = recommender.getMId();
        this.recommanderImg = recommender.getMProfileUrl();
        this.recommanderName = recommender.getMName();
        this.recommanderGrade = recommender.getMGrade();
        this.tTitle = tTitle;
        this.tIntroduce = tIntroduce;
    }

}
