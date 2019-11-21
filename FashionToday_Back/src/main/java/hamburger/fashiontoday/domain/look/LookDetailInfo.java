package hamburger.fashiontoday.domain.look;

import hamburger.fashiontoday.domain.lookitem.Lookitem;
import hamburger.fashiontoday.domain.member.Member;
import hamburger.fashiontoday.domain.tmplook.TmpLook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LookDetailInfo {

    private String remark = "fail";

    private int look_id;

    private String look_image;

    private String look_title;

    private String look_introduction;

    private LookDetailRecommender recommender;

    private String user_profile_image;

    private String user_name;

    private List<LookDetailclothes> clothes_array = new ArrayList<>();

    public LookDetailInfo(TmpLook tmpLook, List<Lookitem> lookitems) {
        this.recommender = new LookDetailRecommender(tmpLook);
        this.look_id = tmpLook.getTLId();
        this.look_image = tmpLook.getTlUrl();
        this.look_title = tmpLook.getTTitle();
        this.look_introduction = tmpLook.getTIntroduce();

        for (Lookitem lookitem : lookitems) {
            clothes_array.add(new LookDetailclothes(lookitem));
        }
    }

    public LookDetailInfo(Look look, Member targetMember, TmpLook tmpLook, List<Lookitem> lookitems) {
        this.recommender = new LookDetailRecommender(tmpLook);
        this.look_id = look.getKId();
        this.look_image = tmpLook.getTlUrl();
        this.look_title = tmpLook.getTTitle();
        this.look_introduction = tmpLook.getTIntroduce();
        this.user_profile_image = targetMember.getMProfileUrl();
        this.user_name = targetMember.getMName();

        for (Lookitem lookitem : lookitems) {
            clothes_array.add(new LookDetailclothes(lookitem));
        }
    }

}

@Getter
@Setter
@NoArgsConstructor
class LookDetailRecommender {

    private int id;

    private String name;

    private String profile_image;

    private int grade;

    LookDetailRecommender(TmpLook tmpLook) {

        this.id = tmpLook.getRecommandMId();
        this.name = tmpLook.getRecommanderName();
        this.profile_image = tmpLook.getRecommanderImg();
        this.grade = tmpLook.getRecommanderGrade();
    }

}

@Getter
@Setter
@NoArgsConstructor
class LookDetailclothes {

    int clothes_id;

    String color;

    String category;

    String clothes_image;

    LookDetailclothes(Lookitem lookitem) {
        this.clothes_id = lookitem.getKmId();
        this.color = lookitem.getColor();
        this.category = lookitem.getLookItemCat();
        this.clothes_image = lookitem.getKItemPicture();
    }

}