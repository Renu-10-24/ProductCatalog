package dev.ren.productCatalog.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@MappedSuperclass
public class BaseModel {
    //String name;
    @Id
    @UuidGenerator(style=UuidGenerator.Style.RANDOM)
    @Column(name="id",columnDefinition="binary(16)", nullable=false, updatable = false, insertable = false)
    private UUID uuid;
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO) //alternate ID  - strategy design pattern
//    protected Long id;
}
