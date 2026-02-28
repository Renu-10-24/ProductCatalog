package dev.ren.productCatalog.inheritanceExample.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue(value="3")
public class Mentor extends User {
    private int noOfSessions;
    private int noOfMentees;

}
