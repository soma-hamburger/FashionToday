package hamburger.fashiontoday.domain.member;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;

@Entity
@Getter
@Setter
@Table(name = "member")
public class Member  {

    @Id
    @Column(name = "mid")
    private int mId;

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
    @ColumnDefault("0")
    private int mStar;

    @Column(name = "mprofileurl")
    private String mProfileUrl;

    @Column(name = "mcomment")
    @ColumnDefault("")
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

    public Member(HashMap<String, Object> userInfo){

        LocalDateTime localDateTime = LocalDateTime.now();

        this.mId = Integer.parseInt(userInfo.get("id").toString());
        this.mName = userInfo.get("nickname").toString();
        this.mMail = userInfo.get("email").toString();
        this.mProfileUrl = userInfo.get("profile").toString();
        this.mSocialKind = "kakao";
        this.mcDateTime = localDateTime.toString();
        this.mcDate = String.valueOf(localDateTime.getYear())+String.valueOf(localDateTime.getMonth())+String.valueOf(localDateTime.getDayOfMonth());
        this.mcTime = String.valueOf(localDateTime.getHour())+String.valueOf(localDateTime.getMinute())+String.valueOf(localDateTime.getSecond());

    }

//    public Member(String mName, String mMail, String mBirthday, String mSocialKind, String mHashVal, String mSocialId, int mStar, String mProfileUrl, String mComment, String mcDateTime, String mcDate, String mcTime, String mConDateTime) {
//        this.mName = mName;
//        this.mMail = mMail;
//        this.mBirthday = mBirthday;
//        this.mSocialKind = mSocialKind;
//        this.mHashVal = mHashVal;
//        this.mSocialId = mSocialId;
//        this.mStar = mStar;
//        this.mProfileUrl = mProfileUrl;
//        this.mComment = mComment;
//        this.mcDateTime = mcDateTime;
//        this.mcDate = mcDate;
//        this.mcTime = mcTime;
//        this.mConDateTime = mConDateTime;
//    }

}
