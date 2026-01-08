package dev.ren.productCatalog.models;

import lombok.Data;

@Data
public class Product extends BaseModel {
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
}
