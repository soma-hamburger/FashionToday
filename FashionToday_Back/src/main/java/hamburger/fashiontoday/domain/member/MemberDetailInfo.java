package hamburger.fashiontoday.domain.member;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDetailInfo {

    private String remark = "fail";

    private int user_id;

    private String name;

    private String birth;

    private String profile_image;

    private String self_intrroduction;

    private int star;

    private int grade;

    public void setMember(Member member){
        this.remark = "success";
        this.user_id = member.getMId();
        this.name = member.getMName();
        this.birth = member.getMBirthday();
        this.profile_image = member.getMProfileUrl();
        this.self_intrroduction = member.getMComment();
        this.star = member.getMStar();
        this.grade = member.getMGrade();
    }

}
