package hamburger.fashiontoday.domain.tmplook;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TmpLookListInfo {

    private String remark = "error";

    private int look_num = 0;

    private int star;

    private List<CandidateLook> daily_look_array = new ArrayList<CandidateLook>();

    public void addTmpLook(TmpLook tmpLook){
        remark = "success";
        look_num++;
        daily_look_array.add(new CandidateLook(tmpLook));
    }

}

@Getter
@Setter
@NoArgsConstructor
class CandidateLook{

    private int look_id;

    private int recommender_id;

    private String recommender_profile_image;

    private String look_image;

    private String title;

    private String introduction;


    public CandidateLook(TmpLook tmpLook) {
        this.look_id = tmpLook.getTLId();
        this.recommender_id = tmpLook.getRecommandMId();
        this.recommender_profile_image = tmpLook.getRecommanderImg();
        this.look_image = tmpLook.getTlUrl();
        this.title = tmpLook.getTTitle();
        this.introduction = tmpLook.getTIntroduce();
    }
}
