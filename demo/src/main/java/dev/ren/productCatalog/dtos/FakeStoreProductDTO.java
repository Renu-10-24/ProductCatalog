package dev.ren.productCatalog.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.ren.productCatalog.models.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@ToString
public class FakeStoreProductDTO {
    private int id;
    private String title;
    private double price;
    private String description;
    private Rating rating;
    private String image;
    private String category;

}
