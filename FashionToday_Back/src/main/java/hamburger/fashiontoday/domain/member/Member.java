package hamburger.fashiontoday.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @프로그램ID : HAM-PB-2015-J
 * @프로그램명 : Member.java
 * @author : 심기성
 * @date : 2019.09.01
 * @version : 0.5
 *
 */
@Entity
@Getter
@Setter
@Table(name = "member")
@NoArgsConstructor
public class Member {

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

    @Column(name = "mpstar")
    @ColumnDefault("0")
    private int mpStar;

    @Column(name = "meditor")
    @ColumnDefault("0")
    private int mEditor;

    @Column(name = "mgrade")
    @ColumnDefault("0")
    private int mGrade;

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



    public Member(HashMap<String, Object> userInfo) {

        LocalDateTime localDateTime = LocalDateTime.now();
        update(userInfo);

        this.mcDateTime = localDateTime.toString();
        this.mcDate = String.valueOf(localDateTime.getYear()) + String.valueOf(localDateTime.getMonth()) + String.valueOf(localDateTime.getDayOfMonth());
        this.mcTime = String.valueOf(localDateTime.getHour()) + String.valueOf(localDateTime.getMinute()) + String.valueOf(localDateTime.getSecond());
    }

    public Member(int mId, String mName, String mMail, String mBirthday, String mSocialKind, String mHashVal, int mStar, int mpStar, int mEditor, int mGrade, String mProfileUrl, String mComment) {
        this.mId = mId;
        this.mName = mName;
        this.mMail = mMail;
        this.mBirthday = mBirthday;
        this.mSocialKind = mSocialKind;
        this.mHashVal = mHashVal;
        this.mSocialId = mSocialId;
        this.mStar = mStar;
        this.mpStar = mpStar;
        this.mEditor = mEditor;
        this.mGrade = mGrade;
        this.mProfileUrl = mProfileUrl;
        this.mComment = mComment;
    }

    public void update(HashMap<String, Object> userInfo) {
        this.mId = Integer.parseInt(userInfo.get("id").toString());
        this.mName = getInfo(userInfo, "nickname");
        this.mBirthday = getInfo(userInfo, "birthday");
        this.mSocialId = getInfo(userInfo, "email");
        this.mMail = getInfo(userInfo, "email");
        this.mProfileUrl = getInfo(userInfo, "profile");
        this.mSocialKind = "kakao";
    }

    // 정보 넑값과 아닌것 구분해 내서 꺼내오기
    private String getInfo(HashMap userInfo, String info) {
        if (userInfo.get(info) == null) {
            return null;
        }

        return userInfo.get(info).toString();
    }


}
