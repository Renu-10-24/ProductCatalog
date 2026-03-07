package dev.ren.productCatalog.models;

import dev.ren.productCatalog.repositories.CategoryRepository;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Setter
@Getter
@Entity
@ToString // to
@NoArgsConstructor
public class Category extends BaseModel{
    private String name;
    @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE,CascadeType.PERSIST}) //saving a category, should save the products added to that category
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
        if(products != null)
            return Collections.unmodifiableList(products);
        return null;
    }
    public void addProduct(Product product) {
        if (this.products == null) {
            this.products = new ArrayList<>();
        }

        // Prevent adding the same product twice
        if (!products.contains(product)) {
            products.add(product);
            product.setCategory(this);
        }
    }

}
