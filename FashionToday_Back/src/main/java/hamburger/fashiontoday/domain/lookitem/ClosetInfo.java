package hamburger.fashiontoday.domain.lookitem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ClosetInfo {

    private String remark = "fail";

    private List<LookitemInfo> clothes_array;

    public void setClothesList(List<Lookitem> clothes_array){
        remark = "success";
        for(Lookitem lookitem : clothes_array){
            this.clothes_array.add(new LookitemInfo(lookitem));
        }
    }

}
