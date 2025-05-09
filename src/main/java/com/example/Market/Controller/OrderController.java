package com.example.Market.Controller;

import com.example.Market.entities.order.Order;
import com.example.Market.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/order")
public class OrderController extends AbstractController<Order> {
    @Autowired
    public OrderController(OrderService repository) {super(repository);}
}
