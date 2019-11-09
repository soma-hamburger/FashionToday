package hamburger.fashiontoday.domain.lookitem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ClosetInfo {

    private String remark = "fail";

    private List<ClosetItemInfo> clothes_array = new ArrayList<ClosetItemInfo>();

    public void setClothesList(List<Lookitem> clothes_array) {
        remark = "success";
        for (Lookitem lookitem : clothes_array) {
            this.clothes_array.add(new ClosetItemInfo(lookitem));
        }
    }

}

@NoArgsConstructor
@Getter
@Setter
class ClosetItemInfo {

    private String remark = "fail";

    private int mid;

    private int kmid;

    private String kitemPicture;

    private String lookItemCat;

    private String kItemColSumPicture;

    public ClosetItemInfo(Lookitem lookitem) {
        remark = "success";
        mid = lookitem.getMId();
        kmid = lookitem.getKmId();
        kitemPicture = lookitem.getKItemPicture();
        lookItemCat = lookitem.getLookItemCat();
        kItemColSumPicture = lookitem.getKItemColSumPicture();
    }

}