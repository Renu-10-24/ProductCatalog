package dev.ren.productCatalog.models;

import dev.ren.productCatalog.repositories.CategoryRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@NoArgsConstructor
@ToString(exclude = {"category"})// to fix the Json serialization recursion when toString() is called recursively on Category and inside category on each product, throws StackOverflowError
public class Product extends BaseModel{
    private String name;
    private String description;
    private String image;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY) //
    private Category category;
    private double price;
    public Product(String name, String description, String image, Category category, double price){
        this.name = name;
        this.description=description;
        this.image = image;
        this.category = category;
        this.price=price;
    }
    public Product(String name){
        this.name = name;
    }
}
