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

}
