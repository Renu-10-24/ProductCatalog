package dev.ren.productCatalog.services;

import dev.ren.productCatalog.dtos.FakeStoreProductDTO;
import dev.ren.productCatalog.dtos.GenericProductDTO;
import dev.ren.productCatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("selfImplProductService")
public class SelfImplProductService implements ProductService{
    @Override
    public GenericProductDTO getProductById(long id){
        System.out.println("in the selfimpl service class");
        System.out.println(id);
        return null;
    }

    @Override
    public ResponseEntity<FakeStoreProductDTO[]> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO dto) {
        return new GenericProductDTO(dto.id(),dto.title(),dto.price(),dto.description(),dto.image(),dto.category());
    }

    @Override
    public GenericProductDTO deleteProductById(long id) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public ResponseEntity<FakeStoreProductDTO> updateProductById(GenericProductDTO product, long id) {
        return null;
    }
}
