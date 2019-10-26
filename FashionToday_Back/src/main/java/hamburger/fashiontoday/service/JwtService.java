package hamburger.fashiontoday.service;

import java.util.Map;

/**
 * @author : 심기성
 * @version : 0.5
 * @프로그램ID : HAM-PB-4003-J
 * @프로그램명 : JwtService.java
 * @date : 2019.10.08
 */
public interface JwtService {
    <T> String create(String key, T data, String subject);
    Map<String, Object> get(String key,String jwt);
    int getMember(String Authorization);
    boolean isUsable(String jwt);

}