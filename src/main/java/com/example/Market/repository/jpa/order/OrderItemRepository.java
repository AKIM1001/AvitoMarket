package com.example.Market.repository.jpa.order;

import com.example.Market.entities.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
