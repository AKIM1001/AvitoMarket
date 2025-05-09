package com.example.Market.repository.redis;

import com.example.Market.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRedisRepository extends AbstractRedisRepository<Product> {
    @Autowired
    public ProductRedisRepository(RedisTemplate<String, Object> redisTemplate) {
        super(redisTemplate, "product:", Product.class);
    }
}
