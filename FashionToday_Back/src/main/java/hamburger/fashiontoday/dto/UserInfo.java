package hamburger.fashiontoday.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {

    private int user_id;

    private String name;

    private int star;

    private String profile_image;

    private int msec;

    private String apiseq;


    public UserInfo(int user_id, String name, int star, String profile_image, int msec, String apiseq) {
        this.user_id = user_id;
        this.name = name;
        this.star = star;
        this.profile_image = profile_image;
        this.msec = msec;
        this.apiseq = apiseq;
    }

}
