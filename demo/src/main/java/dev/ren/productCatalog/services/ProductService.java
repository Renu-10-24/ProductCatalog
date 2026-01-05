package dev.ren.productCatalog.services;

import dev.ren.productCatalog.dtos.FakeStoreProductDTO;
import dev.ren.productCatalog.dtos.GenericProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    GenericProductDTO getProductById(long id);

    List<FakeStoreProductDTO> getAllProducts();

    GenericProductDTO createProduct(GenericProductDTO genericProductDTO);
    String deleteProductById(long id);

    void updateProductById(GenericProductDTO product, long id);
}
