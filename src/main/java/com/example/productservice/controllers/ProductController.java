package com.example.productservice.controllers;

import com.example.productservice.dtos.ProductResponseDto;
import com.example.productservice.models.Product;
import com.example.productservice.services.FakeStoreProductService;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponseDto> getProductbyId(@PathVariable("id") Long id) {

    ProductResponseDto productResponseDto = new ProductResponseDto();
//        productResponseDto.setId(1L);
//        productResponseDto.setDescription("Descriptioin");
//        productResponseDto.setPrice(15.0);
//        productResponseDto.setCategoryName("Cat1");
//        productResponseDto.setTitle("Title1");
//        productResponseDto.setImageUrl("abc.com");

        Product product = productService.getProductById(id);
        productResponseDto = product.fromProduct();

        ResponseEntity<ProductResponseDto> responseEntity = new ResponseEntity<>(
                productResponseDto, HttpStatusCode.valueOf(202)
        );
        return  responseEntity;
    }
    @GetMapping("/product")
    public String getAllProducts() {
        return "All products";
    }
}
