package dev.ren.productCatalog.controllers;


import dev.ren.productCatalog.dtos.FakeStoreProductDTO;
import dev.ren.productCatalog.dtos.GenericProductDTO;
import dev.ren.productCatalog.services.FakeStoreProductService;
import dev.ren.productCatalog.services.ProductService;
import dev.ren.productCatalog.services.exceptions.ExceptionDTO;
import dev.ren.productCatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("/products")
@RequestMapping("/products")
public class ProductController {

//    private final FakeStoreProductService fakeStoreProductService;
    private ProductService productService;

    @Autowired
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }
    //@GetMapping("/products")
    @GetMapping("")
    public ResponseEntity<FakeStoreProductDTO[]> getAllProducts(){
        return productService.getAllProducts();
    }
    //handles requests that look like http://localhost:8080/products/123
    @GetMapping( "{id}")
    public GenericProductDTO getProductById(@PathVariable("id") long id) throws ResourceNotFoundException{
                GenericProductDTO genericProductDTO = productService.getProductById(id);
                if(genericProductDTO == null){
                    throw new ResourceNotFoundException("Product Doesn't exist");
                }
                return genericProductDTO;
    }
    @DeleteMapping("{id}")
    public GenericProductDTO deleteProductById(@PathVariable("id") long id) throws ResourceNotFoundException {
        return productService.deleteProductById(id);
    }
    @PostMapping
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO product){
        return productService.createProduct(product);
    }
    @PutMapping("{id}")
    public ResponseEntity<FakeStoreProductDTO> updateProductById(@RequestBody GenericProductDTO product, @PathVariable("id") long id){
        return productService.updateProductById(product,id);
    }
    //Exception handler methods can be defined in the controller itself like this or moved to a class annotated with @ControllerAdvice - to make them globally accessible by all controllers

}
