package hamburger.fashiontoday.domain.schedule;


import hamburger.fashiontoday.domain.look.Look;
import hamburger.fashiontoday.domain.lookitem.Lookitem;
import hamburger.fashiontoday.domain.tmplook.TmpLook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleDetailInfo {

    private String remark = "fail";

    private int user_id;

    private String date;

    private String state;

    private int star;

    private String title;

    private String intorduce;

    private LookDetailRecommender recommender = new LookDetailRecommender();

    private List<LookDetailclothes> clothes_array = new ArrayList<>();

    public void setPast(Schedule schedule){
        this.user_id = schedule.getMId();
        this.date = schedule.getDdate();
        this.state = "past";
        this.star = schedule.getDStar();
        this.title = schedule.getDTitle();
        this.intorduce = schedule.getDIntroduce();
    }

    public void unSelect(Schedule schedule){
        this.user_id = schedule.getMId();
        this.date = schedule.getDdate();
        this.state = "unselect";
        this.star = schedule.getDStar();
        this.title = schedule.getDTitle();
        this.intorduce = schedule.getDIntroduce();
    }

    public ScheduleDetailInfo(Schedule schedule, Look look, TmpLook tmpLook, List<Lookitem> lookitems){
        this.user_id = schedule.getMId();
        this.date = schedule.getDdate();
        this.state = "select";
        this.star = schedule.getDStar();
        this.title = schedule.getDTitle();
        this.intorduce = schedule.getDIntroduce();
        this.recommender = new LookDetailRecommender(tmpLook);
        for(int i = 0; i<lookitems.size();i++){
            clothes_array.add(new LookDetailclothes(lookitems.get(i)));
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