package dev.ren.productCatalog.models;

import dev.ren.productCatalog.repositories.CategoryRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@NoArgsConstructor
@ToString
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    private double price;
    public Product(String title, String description, String image, Category category, double price){
        this.title = title;
        this.description=description;
        this.image = image;
        this.category = category;
        this.price=price;
    }
    public Product(String title){
        this.title = title;
    }
}
