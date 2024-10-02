package com.example.productservice.controllers;

import com.example.productservice.dtos.ErrorDto;
import com.example.productservice.dtos.ProductRequestDto;
import com.example.productservice.dtos.ProductResponseDto;
import com.example.productservice.models.Product;
import com.example.productservice.services.FakeStoreProductService;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController(@Qualifier("fakeStoreService") ProductService productService) {
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
        productResponseDto = productResponseDto.fromProduct(product);

        ResponseEntity<ProductResponseDto> responseEntity = new ResponseEntity<>(
                productResponseDto, HttpStatusCode.valueOf(202)
        );
        return  responseEntity;
    }
    @GetMapping("/product")
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for (Product product : products) {
            ProductResponseDto productResponseDto = new ProductResponseDto();
            productResponseDto=productResponseDto.fromProduct(product);
            productResponseDtos.add(productResponseDto);
        }
        return productResponseDtos;
    }
    @PostMapping("/product")
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.addProduct(productRequestDto.getTitle(),
                productRequestDto.getDescription(),productRequestDto.getPrice(),
                productRequestDto.getImage(),productRequestDto.getCategory()
        );

        ProductResponseDto productResponseDto = new ProductResponseDto();
        return productResponseDto.fromProduct(product);
    }

    @GetMapping("/products")
    public List<ProductResponseDto> getProductsByLimit(@RequestParam(value = "limit", required = false) Long limit) {
        List<Product> products = productService.getProductsByLimit(limit);
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for (Product product : products) {
            ProductResponseDto productResponseDto = new ProductResponseDto();
            productResponseDto=productResponseDto.fromProduct(product);
            productResponseDtos.add(productResponseDto);
        }
        return productResponseDtos;
    }

    @ExceptionHandler(NullPointerException.class)
    public ErrorDto nullPointerExceptionHandler(){
        ErrorDto errorDto =  new ErrorDto();

        errorDto.setStatus("Failure");
        errorDto.setMessage("Null Pointer Exception/Something went wrong");
        return errorDto;
    }
}
