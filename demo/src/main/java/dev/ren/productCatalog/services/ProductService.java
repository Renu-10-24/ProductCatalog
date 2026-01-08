package dev.ren.productCatalog.services;

import dev.ren.productCatalog.dtos.FakeStoreProductDTO;
import dev.ren.productCatalog.dtos.GenericProductDTO;
import dev.ren.productCatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    GenericProductDTO getProductById(long id);

    ResponseEntity<FakeStoreProductDTO[]> getAllProducts();

    GenericProductDTO createProduct(GenericProductDTO genericProductDTO);
    ResponseEntity<GenericProductDTO> deleteProductById(long id) throws ResourceNotFoundException;

    void updateProductById(GenericProductDTO product, long id);
}
