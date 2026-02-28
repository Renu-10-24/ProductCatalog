package dev.ren.productCatalog.inheritanceExample.joinedtable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="jt_instructor")
@PrimaryKeyJoinColumn(name="user_id")
@DiscriminatorValue(value="I")
public class Instructor extends User {
    private boolean isHandsome;
}
