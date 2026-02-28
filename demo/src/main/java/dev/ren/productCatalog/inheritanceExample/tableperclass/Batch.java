package dev.ren.productCatalog.inheritanceExample.tableperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Batch {
    @Id
    private Long id;
    private String name;

    @ManyToOne
    private Instructor instructor;

}
