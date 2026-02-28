package dev.ren.productCatalog.inheritanceExample.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="mpsc_instructor")
public class Instructor extends User {
    private boolean handsome;
    public Instructor(String email, boolean handsome){
        super(email);
        this.handsome = handsome;
    }
}
