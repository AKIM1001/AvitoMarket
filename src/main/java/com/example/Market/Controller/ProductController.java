package com.example.Market.Controller;

import com.example.Market.entities.Product;
import com.example.Market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController extends AbstractController<Product> {
    @Autowired
    public ProductController(ProductService repository) {super(repository);}

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<Product> findAll(Product product) {
        return super.findAll(product);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN, OWNER')")
    public void deleteById(@PathVariable long id) throws Exception {
        super.deleteById(id);
    }
}

