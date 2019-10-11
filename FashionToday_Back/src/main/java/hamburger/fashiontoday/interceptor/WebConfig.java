package hamburger.fashiontoday.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final String[] EXCLUDE_PATHS = {
            "/login/**"
    };

//    private static final String[] ALLOW_HEADERS = {
//            "X-Requested-With", "Origin", "Content-Type", "Accept",
//            "Authorization", "Access-Control-Allow-Credentials", "Access-Control-Allow-Headers", "Access-Control-Allow-Methods",
//            "Access-Control-Allow-Origin", "Access-Control-Expose-Headers", "Access-Control-Max-Age",
//            "Access-Control-Request-Headers", "Access-Control-Request-Method", "Age", "Allow", "Alternates",
//            "Content-Range", "Content-Disposition", "Content-Description"
//    };

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATHS);
    }

}

