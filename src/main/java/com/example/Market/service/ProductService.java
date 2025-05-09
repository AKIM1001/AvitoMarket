package com.example.Market.service;

import com.example.Market.entities.Product;
import com.example.Market.repository.jpa.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService extends AbstractService<Product> {
    @Autowired
    private ProductService(ProductRepository repository) {super(repository);}

    Product product = Product.builder()
            .name("iPhone 15")
            .description("Brand new, unopened")
            .price(new BigDecimal("999.99"))
            .build();
}
