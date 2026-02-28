package dev.ren.productCatalog.inheritanceExample.joinedtable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="jt_ta")
@PrimaryKeyJoinColumn(name="user_id")
public class TA extends User {
    private int averageRating;
}
