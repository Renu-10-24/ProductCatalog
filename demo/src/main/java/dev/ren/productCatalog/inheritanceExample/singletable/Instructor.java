package dev.ren.productCatalog.inheritanceExample.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue(value="2")
public class Instructor extends User {
    private boolean isHandsome;
}
