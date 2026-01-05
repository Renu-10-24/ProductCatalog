package dev.ren.productCatalog.services;

import dev.ren.productCatalog.dtos.FakeStoreProductDTO;
import dev.ren.productCatalog.dtos.GenericProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("selfImplProductService")
public class SelfImplProductService implements ProductService{
    @Override
    public GenericProductDTO getProductById(long id){
        System.out.println("in the selfimpl service class");
        System.out.println(id);
        return null;
    }

    @Override
    public List<FakeStoreProductDTO> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO dto) {
        return new GenericProductDTO(dto.id(),dto.title(),dto.price(),dto.description(),dto.image(),dto.category());
    }

    @Override
    public String deleteProductById(long id) {
        return "";
    }

    @Override
    public void updateProductById(GenericProductDTO product, long id) {

    }
}
