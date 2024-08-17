package com.example.productservice.services;

import com.example.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public Product getProductById(Long id);

    public List<Product> getAllProducts();
    public Product addProduct(String title, String description, Double price, String image, String category);
    public List<Product> getProductsByLimit(Long id);
}


