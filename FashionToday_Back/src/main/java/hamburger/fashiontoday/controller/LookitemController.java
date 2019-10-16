package hamburger.fashiontoday.controller;

import hamburger.fashiontoday.domain.lookitem.LookitemInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @프로그램ID : HAM-PB-1005-J
 * @프로그램명 : LookitemController.java
 * @author : 심기성
 * @date : 2019.10.15
 * @version : 0.5
 *
 */
@RestController
public class LookitemController {

    // 로그를 찍기 위한 Logger
    private static Logger logger = LogManager.getLogger(LookitemController.class);

    // 302번 api
    // 룩을 저장하는 api
    public LookitemInfo uploadLookitem(@RequestHeader(value = "Authorization")String token,@RequestBody Map<String, Object> param){

        // 로그인 여부 확인
        try{

        }catch (Exception e){

        }


        // 파라미터 파싱
        try{

        }catch (Exception e){

        }




        return new LookitemInfo();
    }



}
