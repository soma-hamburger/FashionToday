package hamburger.fashiontoday.domain.lookitem;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @프로그램ID : HAM-PB-3003-J
 * @프로그램명 : LookitemInfo.java
 * @author : 심기성
 * @date : 2019.10.15
 * @version : 0.5
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class LookitemInfo {

    private String remark;

    private int mid;

    private int kmid;

    private String kitemPicture;

    private String lookItemCat1;

    private String lookItemCat2;

    private String kItemColSumPicture;

    public LookitemInfo(Lookitem lookitem){
        remark = "success";
        mid = lookitem.getMId();
        kmid = lookitem.getKmId();
        kitemPicture = lookitem.getKItemPicture();
        lookItemCat1 = lookitem.getLookItemCat1();
        lookItemCat2 = lookitem.getLookItemCat2();
        kItemColSumPicture = lookitem.getKItemColSumPicture();
    }

    public void fail(){
        remark = "fail";
    }

}
