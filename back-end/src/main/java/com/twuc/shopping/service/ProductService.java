package com.twuc.shopping.service;

import com.twuc.shopping.dto.Product;
import com.twuc.shopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll()
                .stream()
                .map(productEntity -> Product.builder()
                        .name(productEntity.getName())
                        .price(productEntity.getPrice())
                        .units(productEntity.getUnits())
                        .imageUrl(productEntity.getImageUrl())
                        .build())
                .collect(Collectors.toList());
        return products;
    }
}
