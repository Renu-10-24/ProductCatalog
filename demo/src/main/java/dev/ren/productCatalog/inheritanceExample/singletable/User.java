package dev.ren.productCatalog.inheritanceExample.singletable;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="sc_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(value="1")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //multiple ways to do something - strategy design pattern
    protected Long id;
    private String email;
    private String password;

    public User(String email) {
        this.email =email;
    }
}
