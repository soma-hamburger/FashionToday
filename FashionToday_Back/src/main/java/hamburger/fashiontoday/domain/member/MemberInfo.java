package hamburger.fashiontoday.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @프로그램ID : HAM-PB-3001-J
 * @프로그램명 : MemberInfo.java
 * @author : 심기성
 * @date : 2019.09.01
 * @version : 0.5
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class MemberInfo {

    private String remark = "fail";

    private int user_id;

    private String name;

    private int star;

    private String profile_image;

    private int msec;

    private String apiseq;

    private boolean select = true;


    public MemberInfo(int user_id, String name, int star, String profile_image, int msec, String apiseq) {
        remark = "success";
        this.user_id = user_id;
        this.name = name;
        this.star = star;
        this.profile_image = profile_image;
        this.msec = msec;
        this.apiseq = apiseq;
    }

    public void unSelect(){
        this.select = false;
    }

}
