package dev.ren.productCatalog.inheritanceExample.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="mpsc_mentor")
public class Mentor extends User {
    private int noOfSessions;
    private int noOfMentees;
    public Mentor(String email, String password, int noOfSessions, int noOfMentees){
        super(email,password);
        this.noOfSessions = noOfSessions;
        this.noOfMentees = noOfMentees;
    }
}
