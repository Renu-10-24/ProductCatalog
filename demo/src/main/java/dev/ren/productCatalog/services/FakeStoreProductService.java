package dev.ren.productCatalog.services;

import dev.ren.productCatalog.dtos.FakeStoreProductDTO;
import dev.ren.productCatalog.dtos.GenericProductDTO;
import dev.ren.productCatalog.models.Product;
import dev.ren.productCatalog.services.exceptions.ExceptionDTO;
import dev.ren.productCatalog.services.exceptions.ResourceNotFoundException;
import org.jspecify.annotations.Nullable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Type;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private final RestClient restClient;
    public FakeStoreProductService(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public GenericProductDTO getProductById(long id){
        try {
            System.out.println("in the fakeStore service getProductById");
            ResponseEntity<FakeStoreProductDTO> responseEntity = restClient.get()
                    .uri("/products/{id}",id).retrieve().toEntity(FakeStoreProductDTO.class);
            HttpStatusCode statusCode = responseEntity.getStatusCode();
            FakeStoreProductDTO dto= responseEntity.getBody();//using 2 different DTOs to show we can use only the fields appicable for our api, when using 3rd party api like FakeStore
            if(dto == null){
                return null;
            }
            GenericProductDTO genericProductDTO = new GenericProductDTO(dto.id(),dto.title(),dto.price(),dto.description(),dto.image(),dto.category());
            System.out.println("generic dto: " + genericProductDTO);
            return genericProductDTO;
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

    @Override
    public ResponseEntity<FakeStoreProductDTO[]> getAllProducts() {
//        List<FakeStoreProductDTO> dto = restClient.get()
//                .uri("/products").retrieve().body(new ParameterizedTypeReference<List<FakeStoreProductDTO>>() {});
//        dto.forEach(product ->System.out.println( product.id()+" "+product.title()));
//        return dto;

        //method(HttpMethod) .uri(String, Object…​) .headers(Consumer<HttpHeaders>) .body(Object) .retrieve() .toEntity(ParameterizedTypeReference)
        //similar to exchange method
        List<FakeStoreProductDTO> dto = restClient.method(HttpMethod.GET).uri("/products").retrieve().body(new ParameterizedTypeReference<List<FakeStoreProductDTO>>() {});
        dto.forEach(product ->{if(product != null)System.out.println( product.id()+" "+product.title());});

        //Getting ResponseEntity of array of dto objects
        ResponseEntity<FakeStoreProductDTO[]> dtoArrayResponseEntity= restClient.method(HttpMethod.GET).uri("/products").retrieve()
                .toEntity(new ParameterizedTypeReference<FakeStoreProductDTO[]>() {});
        for(FakeStoreProductDTO productDTO : dtoArrayResponseEntity.getBody()){
            System.out.println(productDTO.id() +" "+productDTO.title());
        }
        return dtoArrayResponseEntity;
    }


    @PostMapping
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO){
        System.out.println("id       "+genericProductDTO.id());
        System.out.println("title    "+genericProductDTO.title());
        ResponseEntity<GenericProductDTO> response = restClient.post()
                .uri("/products")
                .body(genericProductDTO)
                .retrieve()
                .toEntity(GenericProductDTO.class);
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

    @Override
    public ResponseEntity<GenericProductDTO> deleteProductById(long id) throws ResourceNotFoundException {
        restClient.delete()
                .uri("/products/{id}",id)
                .retrieve().toBodilessEntity();

        ResponseEntity<GenericProductDTO> response = restClient.delete()
                .uri("/products/{id}",id)
                .retrieve().toEntity(GenericProductDTO.class);

        ResponseEntity<GenericProductDTO> responseEntity = restClient.method(HttpMethod.DELETE).uri("/products/{id}",id).retrieve()
                .toEntity(new ParameterizedTypeReference<>() {
        });
        if(responseEntity.getBody() == null){
            throw new ResourceNotFoundException("Product with id "+id+" not found");
        }
        System.out.println(responseEntity.getBody());
        System.out.println(responseEntity.getStatusCode());
        return responseEntity;
    }

    @Override
    public GenericProductDTO updateProductById(FakeStoreProductDTO product, int id) {
        //update -- put() .uri(String, Map) .body(Object) .retrieve() .toBodilessEntity()
        ResponseEntity<GenericProductDTO> responseEntity = restClient.method(HttpMethod.PUT).uri("/products/{id}",id).body(product).retrieve().toEntity(new ParameterizedTypeReference<GenericProductDTO>() {});
        System.out.println(responseEntity.getStatusCode());
        System.out.println("Product with "+id+" updated successfully");
        return responseEntity.getBody();
    }




}
