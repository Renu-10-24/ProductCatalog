package dev.ren.productCatalog.inheritanceExample.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="tpc_ta")
public class TA extends User {
    private int averageRating;

    public TA(String email) {
        super(email);
    }
    public TA(String email, int averageRating) {
        super(email);
        this.averageRating = averageRating;
    }
}
