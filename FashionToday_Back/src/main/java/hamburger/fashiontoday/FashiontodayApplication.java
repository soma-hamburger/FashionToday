package hamburger.fashiontoday;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FashiontodayApplication {

    private static Logger logger = LogManager.getLogger(FashiontodayApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FashiontodayApplication.class, args);
    }

}
