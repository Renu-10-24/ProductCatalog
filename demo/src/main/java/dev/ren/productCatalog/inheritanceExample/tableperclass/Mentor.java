package dev.ren.productCatalog.inheritanceExample.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="tpc_mentor")
public class Mentor extends User {
    private int noOfSessions;
    private int noOfMentees;

    public Mentor(String email) {
        super(email);
    }
}
