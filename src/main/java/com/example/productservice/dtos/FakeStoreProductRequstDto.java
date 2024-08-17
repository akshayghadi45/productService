package com.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductRequstDto {
    private String title;
    private Double price;
    private String description;
    private String image;
    private String category;
}
