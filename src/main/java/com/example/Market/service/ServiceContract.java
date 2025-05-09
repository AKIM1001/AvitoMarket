package com.example.Market.service;

import java.util.List;

public interface ServiceContract<X> {
    void save(X x) throws Exception;

    List<X> findAll();

    X findById(long id) throws Exception;

    void deleteById(long id) throws Exception;

}
