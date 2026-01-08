package dev.ren.productCatalog.dtos;
//import jakarta.validation.NotBlank;
//import jakarta.validation.PositiveOrZero;
//to help support validations and default values, fields are always objects, not primitives
public record GenericProductDTO (Integer id, String title, Double price, String description, String image, String category){
    public GenericProductDTO{
        System.out.println("in the custom logic ren");//this works !
        if(id==null){
            id=0;
        }
        if(description == null){
            description = "No description provided";
        }
        if(price == null){
            price = 0.0;
        }
        if(category == null){
            category = "Miscellaneous";
        }
    }
    public GenericProductDTO(String title,double price,String description,String category,String image){
        this(0, title,price,description,image,category);// we cannot skip calling the canonical /default constructor.
        System.out.println("in the order jumbled constructor ren");
    }
}
