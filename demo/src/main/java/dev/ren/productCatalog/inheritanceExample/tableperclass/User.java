package dev.ren.productCatalog.inheritanceExample.tableperclass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="tpc_user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class  User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //multiple ways to do something - strategy design pattern
    protected Long id;
    protected String email;
    private transient String password;

    public User(String email) {
        this.email=email;
    }
}
