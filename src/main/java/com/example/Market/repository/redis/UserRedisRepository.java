package com.example.Market.repository.redis;

import com.example.Market.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRedisRepository extends AbstractRedisRepository<User> {
    @Autowired
    public UserRedisRepository(RedisTemplate<String, Object> redisTemplate) {
        super(redisTemplate, "user:", User.class);
    }
}
