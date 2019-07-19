package hamburger.fashiontoday.domain;

import support.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

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

    private Boolean editor;

    @Column(nullable = false)
    private int credit;

    public User(String userId, String password, String name) {
        this(0L, userId, password, name);
    }

    public User(long id, String userId, String password, String name) {
        super(id);
        this.userId = userId;
        this.password = password;
        this.name = name;
    }


}
