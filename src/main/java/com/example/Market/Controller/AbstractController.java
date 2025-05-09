package com.example.Market.Controller;

import com.example.Market.entities.Product;
import com.example.Market.service.ServiceContract;

import java.util.List;

public abstract class AbstractController<X> implements Controller<X> {
    private final ServiceContract<X> repository;

    protected AbstractController(ServiceContract<X> repository) {
        this.repository = repository;
    }

    @Override
    public void save(X x) throws Exception {
        repository.save(x);
    }

    @Override
    public List<X> findAll(Product product) {
        return repository.findAll();
    }

    @Override
    public X findById(long id) throws Exception {
        return repository.findById(id);
    }

    @Override
    public void deleteById(long id) throws Exception {
        repository.deleteById(id);
    }
}
