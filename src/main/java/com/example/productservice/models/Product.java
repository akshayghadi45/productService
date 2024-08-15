package com.example.productservice.models;

import com.example.productservice.dtos.ProductResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private Category category;

    public ProductResponseDto fromProduct(){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(this.id);
        productResponseDto.setTitle(this.title);
        productResponseDto.setDescription(this.description);
        productResponseDto.setPrice(this.price);
        productResponseDto.setImageUrl(this.imageUrl);
        productResponseDto.setCategoryName(this.category.getName());
        return productResponseDto;
    }
}
