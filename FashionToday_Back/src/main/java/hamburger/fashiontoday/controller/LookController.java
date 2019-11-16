package hamburger.fashiontoday.controller;


import hamburger.fashiontoday.domain.tmplook.TmpLook;
import hamburger.fashiontoday.domain.tmplook.TmpLookListInfo;
import hamburger.fashiontoday.domain.tmplook.TmpLookRepository;
import hamburger.fashiontoday.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "")
public class LookController {

    @Autowired
    JwtService jwtService;

    @Autowired
    TmpLookRepository tmpLookRepository;

    //203
    @GetMapping(value = "/dailylist")
    public TmpLookListInfo dailyList(@RequestHeader(value = "Authorization") String token) {

        int loginMemberId = 0;

        String nowDate = new String();
        LocalDateTime localDateTime = LocalDateTime.now();
        if (localDateTime.getMonthValue() < 10) {
            nowDate = String.valueOf(localDateTime.getYear()) + String.valueOf(localDateTime.getMonth()) + String.valueOf(localDateTime.getDayOfMonth());
        } else {
            nowDate = String.valueOf(localDateTime.getYear()) + String.valueOf(localDateTime.getMonth()) + String.valueOf(localDateTime.getDayOfMonth());
        }
        TmpLookListInfo tmpLookListInfo = new TmpLookListInfo();

        // 로그인 여부 확인
        if (jwtService.isUsable(token)) {
            loginMemberId = jwtService.getMember(token);
            System.out.println("유저 아이디 : " + loginMemberId);

        } else {
            tmpLookListInfo.setRemark("login_error");
            return tmpLookListInfo;
        }

        List<TmpLook> todayTmpLooks = tmpLookRepository.findByMIdAndDdate(loginMemberId, nowDate);
        if (todayTmpLooks.size() > 0) {
            for (int i = 0; i < todayTmpLooks.size(); i++) {
                tmpLookListInfo.addTmpLook(todayTmpLooks.get(i));
            }
        } else {
            tmpLookListInfo.setRemark("no_looks");
            return tmpLookListInfo;
        }
        return tmpLookListInfo;
    }

}
