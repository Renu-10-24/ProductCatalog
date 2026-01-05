package dev.ren.productCatalog.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public record SelfImplProductDTO(int id,String title,double price, String description, Rating rating, String image, String category){}
