package com.example.productservice.services;

import com.example.productservice.models.Product;

import java.util.List;

public class ProductDBService implements ProductService {
    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product addProduct(String title, String description, Double price, String image, String category) {
        return null;
    }

    @Override
    public List<Product> getProductsByLimit(Long id) {
        return List.of();
    }
}
