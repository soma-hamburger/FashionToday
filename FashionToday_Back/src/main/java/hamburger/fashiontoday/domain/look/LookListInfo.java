package hamburger.fashiontoday.domain.look;

import hamburger.fashiontoday.domain.member.Member;
import hamburger.fashiontoday.domain.tmplook.TmpLook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
public class LookListInfo {

    private String remark;

    private int look_num;

    private List<LookListDetail> look_array = new ArrayList<>();

    public void addLook(int lookId, Member member, TmpLook tmpLook){
        look_array.add(new LookListDetail(lookId,member.getMName(),tmpLook));
    }

}

@Getter
@Setter
@NoArgsConstructor
class LookListDetail{

    private int look_id;

    private int user_id;

    private String user_name;

    private boolean is_like;

    private int look_like_num;

    private String user_profile_image;

    private String look_image;

    public LookListDetail(int look_id,String name,TmpLook tmpLook) {
        Random random = new Random();
        this.look_id = look_id;
        this.user_id = tmpLook.getMId();
        this.user_name = name;
        this.is_like = random.nextBoolean();
        this.look_like_num = random.nextInt(100)+1;
        this.user_profile_image = tmpLook.getRecommanderImg();
        this.look_image = tmpLook.getTlUrl();
    }
}