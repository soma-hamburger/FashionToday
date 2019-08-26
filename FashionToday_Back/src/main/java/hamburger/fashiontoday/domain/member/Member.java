package hamburger.fashiontoday.domain.member;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "member")
public class Member  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mid")
    private long mId;

    @Column(name = "mname")
    private String mName;

    @Column(name = "mmail")
    private String mMail;

    @Column(name = "mbirthday")
    private String mBirthday;

    @Column(name = "msocialkind")
    private String mSocialKind;

    @Column(name = "mhashval")
    private String mHashVal;

    @Column(name = "mcdatetime")
    private String mcDateTime;

    @Column(name = "mcdate")
    private String mcDate;

    @Column(name = "mctime")
    private String mcTime;

    @Column(name = "msocialid")
    private String mSocialId;

    public Member(){

    }

    public Member(long id, String mName, String mMail, String mBirthday, String mSocialKind, String mHashVal, String mcDateTime, String mcDate, String mcTime, String mSocialId) {
        this.mName = mName;
        this.mMail = mMail;
        this.mBirthday = mBirthday;
        this.mSocialKind = mSocialKind;
        this.mHashVal = mHashVal;
        this.mcDateTime = mcDateTime;
        this.mcDate = mcDate;
        this.mcTime = mcTime;
        this.mSocialId = mSocialId;
    }

}
