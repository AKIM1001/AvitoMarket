package com.example.Market.repository.redis;

import com.example.Market.entities.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRedisRepository extends AbstractRedisRepository<Order> {
    @Autowired
    public OrderRedisRepository(RedisTemplate<String, Object> redisTemplate) {
        super(redisTemplate, "order:", Order.class);
    }
}
