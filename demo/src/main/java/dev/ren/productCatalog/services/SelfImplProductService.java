package dev.ren.productCatalog.services;

import dev.ren.productCatalog.dtos.GenericProductDTO;
import dev.ren.productCatalog.models.Product;
import org.springframework.stereotype.Service;



@Service("selfImplProductService")
public class SelfImplProductService implements ProductService{
    @Override
    public GenericProductDTO getProductById(Long id){
        System.out.println("in the selfimpl service class");
        System.out.println(id);
        return null;
    }

    @Override
    public String getAllProducts() {
        return "";
    }
}
