package dev.ren.productCatalog.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseModel {
    //String name;
    @Id
    private long id;
}
