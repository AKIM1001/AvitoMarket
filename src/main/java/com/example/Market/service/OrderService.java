package com.example.Market.service;

import com.example.Market.entities.order.Order;
import com.example.Market.entities.order.OrderItem;
import com.example.Market.entities.order.TransactionLog;
import com.example.Market.entities.order.UserBalance;
import com.example.Market.repository.jpa.order.OrderItemRepository;
import com.example.Market.repository.jpa.order.OrderRepository;
import com.example.Market.repository.jpa.order.TransactionLogRepository;
import com.example.Market.repository.jpa.order.UserBalanceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService  {


    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserBalanceRepository balanceRepository;
    private final TransactionLogRepository logRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        UserBalanceRepository balanceRepository,
                        TransactionLogRepository logRepository) {
        this.orderRepository = orderRepository;
        this.balanceRepository = balanceRepository;
        this.logRepository = logRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public void placeOrder(Long userId, List<OrderItem> items) {
        BigDecimal total = items.stream()
                .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        UserBalance balance = balanceRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User balance not found"));

        if (balance.getAmount().compareTo(total) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        balance.setAmount(balance.getAmount().subtract(total));
        balanceRepository.save(balance);

        Order order = new Order();
        order.setUserId(userId);
        order.setTotal(total);
        order.setCreatedAt(LocalDateTime.now());
        order = orderRepository.save(order);

        for (OrderItem item : items) {
            item.setOrder(order);
            orderItemRepository.save(item);
        }

        TransactionLog log = new TransactionLog();
        log.setUserId(userId);
        log.setAction("Placed order with total: " + total);
        log.setTimestamp(LocalDateTime.now());
        logRepository.save(log);
    }

}
