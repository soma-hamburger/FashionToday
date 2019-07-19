package hamburger.fashiontoday.domain;

import support.AbstractEntity;

import javax.persistence.*;

@Entity
public class Clothes extends AbstractEntity {

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_clothes_owner"))
    private User owner;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String type;

    @Column(length = 10, nullable = false)
    private String color;

    @Column(nullable = false)
    private String picture;

    @Column
    private int price;

}
