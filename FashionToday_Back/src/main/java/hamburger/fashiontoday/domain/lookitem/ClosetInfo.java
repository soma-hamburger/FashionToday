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

    private List<Lookitem> clothes_array;

    public void setClothesList(List<Lookitem> clothes_array){
        remark = "success";
        this.clothes_array = clothes_array;
    }

}
