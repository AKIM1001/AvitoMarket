package com.example.Market.service;

import com.example.Market.entities.User;
import com.example.Market.repository.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User> {
    @Autowired
    private UserService(UserRepository repository) {super(repository);};


}
