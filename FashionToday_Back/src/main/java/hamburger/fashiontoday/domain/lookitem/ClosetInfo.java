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

    private int user_id;

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

    private int clothes_id;

    private String clothes_image;

    private String category;

    private String color;

    public ClosetItemInfo(Lookitem lookitem) {
        clothes_id = lookitem.getKmId();
        clothes_image = lookitem.getKItemPicture();
        category = lookitem.getLookItemCat();
        color = lookitem.getColor();
    }

}