package com.example.Market.Controller;

import com.example.Market.entities.Product;

import java.util.List;

public interface Controller<X> {
    void save(X x) throws Exception;
    List<X> findAll(Product product);
    X findById(long id) throws Exception;
    void deleteById(long id) throws Exception;
}
