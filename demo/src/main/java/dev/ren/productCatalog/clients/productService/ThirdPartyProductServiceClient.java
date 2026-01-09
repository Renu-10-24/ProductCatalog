package dev.ren.productCatalog.clients.productService;

import dev.ren.productCatalog.dtos.FakeStoreProductDTO;
import dev.ren.productCatalog.dtos.GenericProductDTO;
import dev.ren.productCatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
//not used currently to avoid complexity, if we are planning to migrate to different third party APIs, we can go for this client interface implementation
public interface ThirdPartyProductServiceClient {
    FakeStoreProductDTO getProductById(long id);

    ResponseEntity<FakeStoreProductDTO[]> getAllProducts();

    FakeStoreProductDTO createProduct(FakeStoreProductDTO fakeStoreProductDTO);

    FakeStoreProductDTO deleteProductById(long id) throws ResourceNotFoundException;

    ResponseEntity<FakeStoreProductDTO> updateProductById(GenericProductDTO product, long id);
}
