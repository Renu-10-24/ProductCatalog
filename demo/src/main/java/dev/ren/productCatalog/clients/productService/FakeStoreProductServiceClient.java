package dev.ren.productCatalog.clients.productService;

import dev.ren.productCatalog.dtos.FakeStoreProductDTO;
import dev.ren.productCatalog.dtos.GenericProductDTO;
import dev.ren.productCatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class FakeStoreProductServiceClient{
    private final RestClient restClient;
    public FakeStoreProductServiceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public FakeStoreProductDTO getProductById(long id){
        try {
            System.out.println("in the fakeStore service getProductById");
            ResponseEntity<FakeStoreProductDTO> responseEntity = restClient.get()
                    .uri("/products/{id}",id).retrieve().toEntity(FakeStoreProductDTO.class);
            HttpStatusCode statusCode = responseEntity.getStatusCode();
            FakeStoreProductDTO dto= responseEntity.getBody();//using 2 different DTOs to show we can use only the fields appicable for our api, when using 3rd party api like FakeStore
            if(dto == null){
                return null;
            }
            return dto;
        }catch(NullPointerException e){
            System.out.println("status : "+e.getMessage());
        }
        catch(HttpClientErrorException e){
            System.out.println("status : "+e.getStatusCode());
            System.out.println("response body : "+e.getResponseBodyAsString());
        }
        System.out.println("returning null");
        return null;
    }

    public ResponseEntity<FakeStoreProductDTO[]> getAllProducts() {

        //method(HttpMethod) .uri(String, ) .headers(Consumer<HttpHeaders>) .body(Object) .retrieve() .toEntity(ParameterizedTypeReference)
        //method in RestClient similar to exchange method in RestTemplate
        List<FakeStoreProductDTO> dto = restClient.method(HttpMethod.GET).uri("/products").retrieve().body(new ParameterizedTypeReference<>() {});
        if(dto !=null){
            dto.forEach(product ->{if(product != null)System.out.println( product.id()+" "+product.title());});
        }

        //Getting ResponseEntity of array of dto objects
        ResponseEntity<FakeStoreProductDTO[]> dtoArrayResponseEntity= restClient.method(HttpMethod.GET).uri("/products").retrieve()
                .toEntity(new ParameterizedTypeReference<>() {});
        if(dtoArrayResponseEntity.getBody() !=null) {
            for (FakeStoreProductDTO productDTO : dtoArrayResponseEntity.getBody()) {
                System.out.println(productDTO.id() + " " + productDTO.title());
            }
        }
        return dtoArrayResponseEntity;
    }

    public FakeStoreProductDTO createProduct(GenericProductDTO product){
        System.out.println("id       "+product.id());
        System.out.println("title    "+product.title());
        ResponseEntity<FakeStoreProductDTO> response = restClient.post()
                .uri("/products")
                .body(product)
                .retrieve()
                .toEntity(FakeStoreProductDTO.class);
        System.out.println("response body - product : "+response.getBody());
        System.out.println("status  : "+response.getStatusCode());
        if(response.getStatusCode().is4xxClientError()){
            System.out.println("Malformed request");
        }
        if(response.getStatusCode().is2xxSuccessful()){
            System.out.println("Product created successfully with id "+response.getBody().id());
        }
        if(response.getStatusCode().is5xxServerError()){
            System.out.println("Server side error");
        }
        return response.getBody();
    }

    public FakeStoreProductDTO deleteProductById(long id) throws ResourceNotFoundException {
        restClient.delete()
                .uri("/products/{id}",id)
                .retrieve().toBodilessEntity();

        ResponseEntity<FakeStoreProductDTO> response = restClient.delete()
                .uri("/products/{id}",id)
                .retrieve().toEntity(FakeStoreProductDTO.class);

        ResponseEntity<FakeStoreProductDTO> responseEntity = restClient.method(HttpMethod.DELETE).uri("/products/{id}",id).retrieve()
                .toEntity(new ParameterizedTypeReference<>() {
                });
        if(responseEntity.getBody() == null){
            throw new ResourceNotFoundException("Product with id "+id+" not found");
        }
        System.out.println(responseEntity.getBody());
        System.out.println(responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    public ResponseEntity<FakeStoreProductDTO> updateProductById(GenericProductDTO product, long id) {
        //update -- put() .uri(String, Map) .body(Object) .retrieve() .toBodilessEntity()
        //do validation if product exists for id and then call update
        ResponseEntity<FakeStoreProductDTO> responseEntity = restClient.method(HttpMethod.PUT).uri("/products/{id}",id).body(product).retrieve().toEntity(new ParameterizedTypeReference<>() {
        });
        System.out.println(responseEntity.getStatusCode());
        System.out.println("Product with "+id+" updated successfully");
        return responseEntity;
    }
}
