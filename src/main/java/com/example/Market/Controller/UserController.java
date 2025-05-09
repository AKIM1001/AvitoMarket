package com.example.Market.Controller;

import com.example.Market.entities.User;
import com.example.Market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController extends AbstractController<User> {
    @Autowired
    public UserController(UserService repository) {super(repository);}
}
