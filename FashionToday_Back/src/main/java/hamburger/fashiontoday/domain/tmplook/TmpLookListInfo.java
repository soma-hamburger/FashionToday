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

    private Recommender recommender = new Recommender();

    private String look_image;

    private String title;

    private String introduction;


    public CandidateLook(TmpLook tmpLook) {
        this.look_id = tmpLook.getTLId();
        this.recommender.setId(tmpLook.getRecommandMId());
        this.recommender.setProfile_image(tmpLook.getRecommanderImg());
        this.recommender.setGrade(tmpLook.getRecommanderGrade());
        this.recommender.setName(tmpLook.getRecommanderName());
        this.look_image = tmpLook.getTlUrl();
        this.title = tmpLook.getTTitle();
        this.introduction = tmpLook.getTIntroduce();
    }

}

@Getter
@Setter
@NoArgsConstructor
class Recommender{

    private int id;

    private String name;

    private int grade;

    private String profile_image;

}