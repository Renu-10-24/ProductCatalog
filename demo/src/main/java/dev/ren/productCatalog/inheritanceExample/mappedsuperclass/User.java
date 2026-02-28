package dev.ren.productCatalog.inheritanceExample.mappedsuperclass;

import jakarta.persistence.*;

@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //multiple ways to do something - strategy design pattern
    protected Long id;
    private String email;
    private String password;

    public User(String email, String password) {
        this.email =email;
        this.password = password;
    }
    public User(String email) {
        this.email =email;
    }
}
