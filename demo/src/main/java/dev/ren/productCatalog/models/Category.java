package dev.ren.productCatalog.models;

import dev.ren.productCatalog.repositories.CategoryRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
@Setter
@Getter
@Entity
@ToString // to
@NoArgsConstructor
public class Category extends BaseModel{
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
//    protected Category{}
    public Category(String name, List<Product> products){
        this.name = name;
        this.products = products;
    }
    public Category(String name){
        this.name = name;
    }
    // DEFENSIVE GETTER
    public List<Product> getProducts() {
        // Return an unmodifiable view to prevent external
        // code from doing: getProducts().add(p)
        // (which would bypass our synchronization logic, developers cannot use category.getProducts.add(product) - it throws UnsupportedOperationException
        return Collections.unmodifiableList(products);
    }
    public void addProduct(Product product) {
        this.products.add(product);
        if (product.getCategory() != this) {
            product.setCategory(this);
        }
    }

}
