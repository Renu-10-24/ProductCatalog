package dev.ren.productCatalog.inheritanceExample.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="mpsc_ta")
public class TA extends User {
    private int averageRating;
    public TA(String email, String password, int averageRating){
        super(email,password);
        this.averageRating = averageRating;
    }
}
