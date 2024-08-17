package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreProductRequstDto;
import com.example.productservice.dtos.FakeStoreProductResponseDto;
import com.example.productservice.models.Product;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getProductById(Long id) {

        FakeStoreProductResponseDto responseDto= restTemplate.getForObject(
                "http://fakestoreapi.com/products/" + id + "/",
                FakeStoreProductResponseDto.class
        );

        return responseDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductResponseDto[] responseDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductResponseDto[].class
        );

        List<Product> products = new ArrayList<>();
        for (FakeStoreProductResponseDto responseDto : responseDtos) {
            products.add(responseDto.toProduct());
        }

        return products;
    }

    @Override
    public Product addProduct(String title, String description, Double price, String image, String category) {
        FakeStoreProductRequstDto fakeStoreProductRequstDto = new FakeStoreProductRequstDto();
        fakeStoreProductRequstDto.setDescription(description);
        fakeStoreProductRequstDto.setTitle(title);
        fakeStoreProductRequstDto.setPrice(price);
        fakeStoreProductRequstDto.setImage(image);
        fakeStoreProductRequstDto.setCategory(category);
       FakeStoreProductResponseDto fakeStoreProductResponseDto =restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fakeStoreProductRequstDto,
               FakeStoreProductResponseDto.class

        );
        return fakeStoreProductResponseDto.toProduct();
    }

    @Override
    public List<Product> getProductsByLimit(Long limit) {
        String url = UriComponentsBuilder
                .fromUriString("https://fakestoreapi.com/products")
                .queryParam("limit", limit)
                .toUriString();

        FakeStoreProductResponseDto[] responseDtos = restTemplate.getForObject(
                url,
                FakeStoreProductResponseDto[].class
        );

        List<Product> products = new ArrayList<>();
        for (FakeStoreProductResponseDto responseDto : responseDtos) {
            products.add(responseDto.toProduct());
        }

        return products;
    }

}
