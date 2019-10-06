package hamburger.fashiontoday.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins="*")
public class IndexController {

    private static Logger logger = LogManager.getLogger(IndexController.class);

    @RequestMapping(value = "/")
    public String indexPage(){
        logger.debug("Success");
        return "index.html";
    }

}
