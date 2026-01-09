package dev.ren.productCatalog.clients.productService;

import dev.ren.productCatalog.dtos.FakeStoreProductDTO;
import dev.ren.productCatalog.dtos.GenericProductDTO;
import dev.ren.productCatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

public interface ThirdPartyProductServiceClient {
    FakeStoreProductDTO getProductById(long id);

    ResponseEntity<FakeStoreProductDTO[]> getAllProducts();

    FakeStoreProductDTO createProduct(FakeStoreProductDTO fakeStoreProductDTO);

    FakeStoreProductDTO deleteProductById(long id) throws ResourceNotFoundException;

    ResponseEntity<FakeStoreProductDTO> updateProductById(GenericProductDTO product, long id);
}
