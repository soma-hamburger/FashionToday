package hamburger.fashiontoday.domain.tmplook;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TmpLookInfo {

    String remark;

    public TmpLookInfo(TmpLook tmpLook){
        this.remark = "success";
    }

}