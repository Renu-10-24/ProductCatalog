package dev.ren.productCatalog.services;

import dev.ren.productCatalog.clients.productService.FakeStoreProductServiceClient;
import dev.ren.productCatalog.dtos.FakeStoreProductDTO;
import dev.ren.productCatalog.dtos.GenericProductDTO;
import dev.ren.productCatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
//instead of directly using RestClient, we create an AdapterClient and wire that, for loose coupling with the third party API
//    private final RestClient restClient;

    private final FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    private GenericProductDTO convertFakeStoreProductIntoGenericProduct(FakeStoreProductDTO fakeStoreProductDto) {
        //canonical constructor in GenericProductDTO - Integer id,String title,Double price,String description,String category,String image
        GenericProductDTO product = new GenericProductDTO(fakeStoreProductDto.id(),fakeStoreProductDto.title(),fakeStoreProductDto.price(),fakeStoreProductDto.description(),fakeStoreProductDto.image(),fakeStoreProductDto.category());
        return product;
    }

    public GenericProductDTO createProduct(GenericProductDTO product) {
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.createProduct(product));
    }

    public GenericProductDTO getProductById(long id) throws ResourceNotFoundException{
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductServiceClient.getProductById(id);
        if(fakeStoreProductDTO == null){
            throw new ResourceNotFoundException("Product doesn't exist");
        }
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductDTO);
    }

    public ResponseEntity<FakeStoreProductDTO[]> getAllProducts() {
        return fakeStoreProductServiceClient.getAllProducts();
    }



    public GenericProductDTO deleteProductById(long id) throws ResourceNotFoundException {
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductServiceClient.deleteProductById(id);
        if(fakeStoreProductDTO == null){
            throw new ResourceNotFoundException("Product doesn't exist");
        }
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductDTO);
    }

    public ResponseEntity<FakeStoreProductDTO> updateProductById(GenericProductDTO product, long id) {
        return fakeStoreProductServiceClient.updateProductById(product, id);
    }
}
