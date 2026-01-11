package dev.ren.productCatalog.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;
    @OneToOne
    private Category category;
    private double price;

}
