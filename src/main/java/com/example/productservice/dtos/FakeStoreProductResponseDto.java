package com.example.productservice.dtos;


import com.example.productservice.models.Product;
import com.example.productservice.models.Category;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductResponseDto {
    private Long id;
    private String title;
    private String description;
    private String category;
    private String price;
    private String image;

    public Product toProduct(){
        Product product = new Product();
        product.setId(this.id);
        product.setTitle(this.title);
        product.setDescription(this.description);
        product.setImageUrl(this.image);
        product.setPrice(Double.valueOf(this.price));

        Category category = new Category();

        category.setName(this.category);
        product.setCategory(category);
        return product;
    }
}
