package dev.ren.productCatalog.inheritanceExample.joinedtable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="jt_mentor")
@PrimaryKeyJoinColumn(name="user_id")
public class Mentor extends User {
    private int noOfSessions;
    private int noOfMentees;

}
