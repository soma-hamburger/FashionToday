package hamburger.fashiontoday.domain;

import org.hibernate.annotations.Where;
import support.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity {

    @Size(min = 3, max = 20)
    @Column(unique = true, nullable = false)
    private String userId;

    @Size(min = 3, max = 20)
    @Column(nullable = false)
    private String password;

    @Size(min = 3, max = 20)
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean editor;

    @Column(nullable = false)
    private int credit = 0;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @OrderBy("date desc")
    List<Clothes> closet = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @OrderBy("date desc")
    List<LookInfo> dailyLooks = new ArrayList<>();

    public User(String userId, String password, String name, Boolean editor) {
        this(0L, userId, password, name, editor);
    }

    public User(long id, String userId, String password, String name, Boolean editor) {
        super(id);
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.editor = editor;
    }


}
