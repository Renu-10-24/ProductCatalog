package dev.ren.productCatalog.inheritanceExample.joinedtable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="jt_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //multiple ways to do something - strategy design pattern
    @PrimaryKeyJoinColumn(name="user_id")
    private Long id;
    private String email;
    private String password;
}