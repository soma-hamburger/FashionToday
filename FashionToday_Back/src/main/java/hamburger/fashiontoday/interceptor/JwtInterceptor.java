package hamburger.fashiontoday.interceptor;

import hamburger.fashiontoday.exception.UnauthorizedException;
import hamburger.fashiontoday.service.JwtService;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    private static final String HEADER_AUTH = "Authorization";

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler, HttpHeaders httpHeaders)
            throws Exception {
        final String token = request.getHeader(HEADER_AUTH);

        System.out.println();
        System.out.println("동훈이 토큰 : "+token);
        System.out.println(request.toString());
        System.out.println();

        if(token != null && jwtService.isUsable(token)){
            return true;
        }else{
            throw new UnauthorizedException();
        }

    }
}