package dev.ren.productCatalog.inheritanceExample.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue(value="4")
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
