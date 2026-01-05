package dev.ren.productCatalog.dtos;

public record FakeStoreProductDTO(Integer id,String title,Double price,String description,String category,String image) {

    public FakeStoreProductDTO(String title,double price,String description,String category,String image){
        this(0, title,price,description,category,image);
    }
    public FakeStoreProductDTO{
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
}
