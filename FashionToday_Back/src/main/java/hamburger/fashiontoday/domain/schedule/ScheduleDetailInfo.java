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

    private String introduce;

    private ScheduleLook look;

    public void setPast(Schedule schedule){
        this.user_id = schedule.getMId();
        this.date = schedule.getDdate();
        this.state = "past";
        this.star = schedule.getDStar();
        this.title = schedule.getDTitle();
        this.introduce = schedule.getDIntroduce();
    }

    public void setFuture(Schedule schedule){
        this.user_id = schedule.getMId();
        this.date = schedule.getDdate();
        this.state = "future";
        this.star = schedule.getDStar();
        this.title = schedule.getDTitle();
        this.introduce = schedule.getDIntroduce();
    }

    public void unSelect(Schedule schedule){
        this.user_id = schedule.getMId();
        this.date = schedule.getDdate();
        this.state = "unselect";
        this.star = schedule.getDStar();
        this.title = schedule.getDTitle();
        this.introduce = schedule.getDIntroduce();
    }

    public ScheduleDetailInfo(Schedule schedule, Look look, TmpLook tmpLook, List<Lookitem> lookitems){
        this.user_id = schedule.getMId();
        this.date = schedule.getDdate();
        this.state = "select";
        this.star = schedule.getDStar();
        this.title = schedule.getDTitle();
        this.introduce = schedule.getDIntroduce();
        this.look = new ScheduleLook(look,tmpLook,lookitems);
    }

}

@Getter
@Setter
@NoArgsConstructor
class ScheduleLook{

    private int id;

    private boolean share;

    private LookDetailRecommender recommender;

    private String look_image;

    private String look_title;

    private String look_introduce;

    private List<LookDetailclothes> clothes_array = new ArrayList<>();

    public ScheduleLook(Look look,TmpLook tmpLook,List<Lookitem> lookitems){
        this.id = look.getKId();
        if(look.getKShare()==0) {
            this.share = false;
        }
        else{
            this.share = true;
        }
        this.recommender = new LookDetailRecommender(tmpLook);
        this.look_image = tmpLook.getTlUrl();
        this.look_title = tmpLook.getTTitle();
        this.look_introduce = tmpLook.getTIntroduce();
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