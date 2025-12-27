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
public class GenericProductDTO {
    private int id;
    private String title;

    @Override
    public String toString() {
        return "GenericProductDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    private double price;
    private String description;
    private Rating rating;
    private String category;
    private String image;

}
