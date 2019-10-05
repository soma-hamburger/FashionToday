package hamburger.fashiontoday.controller;

import hamburger.fashiontoday.domain.lookitem.Lookitem;
import hamburger.fashiontoday.domain.lookitem.LookitemRepository;
import hamburger.fashiontoday.domain.lookitemclass.LookItemClassRepository;
import hamburger.fashiontoday.domain.lookitemclass.LookitemClass;
import hamburger.fashiontoday.domain.lookstructure.LookStructureRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @프로그램ID : HAM-PB-1000-J
 * @프로그램명 : TestController.java
 * @author : 심기성
 * @date : 2019.09.01
 * @version : 0.5
 *
 */
@RestController
@CrossOrigin(origins="*")
public class TestController {

    //
    private static Logger logger = LogManager.getLogger(TestController.class);

    @Autowired
    LookitemRepository lookItemRepository;

    @Autowired
    LookItemClassRepository lookItemClassRepository;

    @Autowired
    LookStructureRepository lookStructureRepository;

    @GetMapping("/lookitem")
    public Lookitem saveLookItem(){
        Lookitem testLookitem = new Lookitem(2,4,"http://pashiontoday.com","하늘하늘한 블라우스","http://pashiontoday.com");
        return lookItemRepository.save(testLookitem);
    }

    @GetMapping("/lookitemclass")
    public LookitemClass saveLookItemClass(){
        LookitemClass testLookItem = new LookitemClass("c","b","clothes","blouse");
        return lookItemClassRepository.save(testLookItem);
    }

}
