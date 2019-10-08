package hamburger.fashiontoday.service;

import hamburger.fashiontoday.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : 심기성
 * @version : 0.5
 * @프로그램ID : HAM-PB-4004-J
 * @프로그램명 : JwtServiceImpl.java
 * @date : 2019.10.08
 */
@Slf4j
@Service("jwtService")
public class JwtServiceImpl implements JwtService{

    // 패션 투데이 시크릿 키
    private static final String pashionKey =  "pahsionTodaySecret";

    //토큰을 만드는 함수
    @Override
    public <T> String create(String key, T data, String subject){
        String jwt = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("regDate", System.currentTimeMillis())
                .setSubject(subject)
                .claim(key, data)
                .signWith(SignatureAlgorithm.HS256, this.generateKey())
                .compact();
        return jwt;
    }

    // 키값을 만들어 주는 함수
    private byte[] generateKey(){
        byte[] key = null;
        try {
            key = pashionKey.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            if(log.isInfoEnabled()){
                e.printStackTrace();
            }else{
                log.error("Making JWT Key Error ::: {}", e.getMessage());
            }
        }
        return key;
    }

    // 토큰의 유효성을 판단해주는 함수
    @Override
    public boolean isUsable(String jwt) {
        try{
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(this.generateKey())
                    .parseClaimsJws(jwt);
            return true;

        }catch (Exception e) {

            if(log.isInfoEnabled()){
                e.printStackTrace();
            }else{
                log.error(e.getMessage());
            }
            throw new UnauthorizedException();
        }
    }

    // 키에서 설정된 값들을 가져오는 함수
    @Override
    public Map<String, Object> get(String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String jwt = request.getHeader("Authorization");
        log.debug("동훈이 키값 : "+jwt);
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(pashionKey.getBytes("UTF-8"))
                    .parseClaimsJws(jwt);
        } catch (Exception e) {
            if(log.isInfoEnabled()){
                e.printStackTrace();
            }else{
                log.error(e.getMessage());
            }
            throw new UnauthorizedException();
        }
        @SuppressWarnings("unchecked")
        Map<String, Object> value = (LinkedHashMap<String, Object>)claims.getBody().get(key);
        return value;
    }

    // 맴버 아이디를 가져오는 함수
    @Override
    public int getMemberId() {
        return (int)this.get("member").get("mId");
    }

}