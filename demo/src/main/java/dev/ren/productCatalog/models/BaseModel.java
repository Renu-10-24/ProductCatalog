package dev.ren.productCatalog.models;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@MappedSuperclass
public class BaseModel {
    //String name;
    @Id
    @UuidGenerator(style=UuidGenerator.Style.RANDOM)
    @Column(name="id",columnDefinition="binary(16)", nullable=false, updatable = false)
    private UUID uuid;
}
