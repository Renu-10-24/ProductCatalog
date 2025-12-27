package dev.ren.productCatalog.services;

import dev.ren.productCatalog.dtos.FakeStoreProductDTO;
import dev.ren.productCatalog.dtos.GenericProductDTO;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    //private RestTemplateBuilder restTemplateBuilder; - auto injected in springboot 4
    private final RestClient restClient;
    public FakeStoreProductService(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public GenericProductDTO getProductById(Long id){
        try {
            System.out.println("in the fakeStore service class");
            ResponseEntity<FakeStoreProductDTO> responseEntity = restClient.get()
                    .uri("/products/{id}",id).retrieve().toEntity(FakeStoreProductDTO.class);
            HttpStatusCode statusCode = responseEntity.getStatusCode();
            FakeStoreProductDTO fakeStoreProductDTO= responseEntity.getBody();
            GenericProductDTO genericProductDTO = new GenericProductDTO();
            if(fakeStoreProductDTO == null){
                return new GenericProductDTO();
            }
            genericProductDTO.setId(fakeStoreProductDTO.getId());
            genericProductDTO.setDescription(fakeStoreProductDTO.getDescription());
            genericProductDTO.setTitle(fakeStoreProductDTO.getTitle());
            genericProductDTO.setPrice(fakeStoreProductDTO.getPrice());
            genericProductDTO.setCategory(fakeStoreProductDTO.getCategory());
            genericProductDTO.setImage(fakeStoreProductDTO.getImage());
            genericProductDTO.setRating(fakeStoreProductDTO.getRating());
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
    public String getAllProducts() {
        return "";
    }
}
