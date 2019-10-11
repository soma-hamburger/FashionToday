package hamburger.fashiontoday.interceptor;

import hamburger.fashiontoday.filter.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final String[] EXCLUDE_PATHS = {
            "/login/**"
    };

    @Autowired
    private JwtInterceptor jwtInterceptor;

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//        .allowedMethods("*")
//        .allowedHeaders("*");
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATHS);
    }

    @Bean
    CorsFilter corsFilter(){
        return new CorsFilter();
    }

}