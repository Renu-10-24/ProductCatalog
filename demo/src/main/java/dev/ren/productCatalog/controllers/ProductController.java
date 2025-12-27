package dev.ren.productCatalog.controllers;

import dev.ren.productCatalog.dtos.FakeStoreProductDTO;
import dev.ren.productCatalog.dtos.GenericProductDTO;
import dev.ren.productCatalog.services.FakeStoreProductService;
import dev.ren.productCatalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController("/products")
@RequestMapping("/products")
public class ProductController {

    private final FakeStoreProductService fakeStoreProductService;
    private ProductService productService;

    @Autowired
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService, FakeStoreProductService fakeStoreProductService){
        this.productService = productService;
        this.fakeStoreProductService = fakeStoreProductService;
    }
    //@GetMapping("/products")
    @GetMapping("")
    public String getAllProducts(){
        return productService.getAllProducts();
    }
    //handles requests that look like http://localhost:8080/products/123
    @GetMapping( "{id}")
    public GenericProductDTO getProductById(@PathVariable("id") Long id){
                return productService.getProductById(id);
    }
    @DeleteMapping("{id}")
    public void deleteProductById(){

    }
    @PostMapping("")
    public String createProduct(){
        return "Returning product with id : " + UUID.randomUUID();
    }
    @PutMapping("{id}")
    public void updateProductById(){

    }
}
