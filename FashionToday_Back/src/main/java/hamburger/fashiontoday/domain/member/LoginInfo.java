package hamburger.fashiontoday.domain.member;


import lombok.Getter;
import lombok.Setter;

/**
 * @프로그램ID : HAM-PB-3002-J
 * @프로그램명 : LoginInfo.java
 * @author : 심기성
 * @date : 2019.10.08
 * @version : 0.5
 *
 */

@Getter
@Setter
public class LoginInfo {


    private String token;

    private int msec;

    private String apiseq;


    public LoginInfo(String token) {
        this.token = token;
    }
}
