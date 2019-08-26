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

    @Column(name = "msocialid")
    private String mSocialId;

    @Column(name = "mstar")
    private int mStar;

    @Column(name = "mprofileurl")
    private String mProfileUrl;

    @Column(name = "mcomment")
    private String mComment;

    @Column(name = "mcdatetime")
    private String mcDateTime;

    @Column(name = "mcdate")
    private String mcDate;

    @Column(name = "mctime")
    private String mcTime;

    @Column(name = "mcondatetime")
    private String mConDateTime;

    public Member(){

    }

    public Member(String mName, String mMail, String mBirthday, String mSocialKind, String mHashVal, String mSocialId, int mStar, String mProfileUrl, String mComment, String mcDateTime, String mcDate, String mcTime, String mConDateTime) {
        this.mName = mName;
        this.mMail = mMail;
        this.mBirthday = mBirthday;
        this.mSocialKind = mSocialKind;
        this.mHashVal = mHashVal;
        this.mSocialId = mSocialId;
        this.mStar = mStar;
        this.mProfileUrl = mProfileUrl;
        this.mComment = mComment;
        this.mcDateTime = mcDateTime;
        this.mcDate = mcDate;
        this.mcTime = mcTime;
        this.mConDateTime = mConDateTime;
    }

}
