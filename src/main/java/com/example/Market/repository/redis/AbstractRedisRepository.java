package com.example.Market.repository.redis;

import lombok.Getter;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
public abstract class AbstractRedisRepository<X> implements RedisRepository<X> {
    private final RedisTemplate<String, Object> redisTemplate;
    private final String prefix;
    private final Class<X> clazz;

    protected AbstractRedisRepository(RedisTemplate<String, Object> redisTemplate,
                                      String prefix, Class<X> clazz) {
        this.redisTemplate = redisTemplate;

        this.prefix = prefix;

        this.clazz = clazz;
    }

    @Override
    public void save(X x, long id) {
        redisTemplate.opsForValue().set(prefix + id, x);
    }

    @Override
    public X getById(long id) throws IllegalStateException {
        Object obj = redisTemplate.opsForValue().get(prefix + id);

        if (clazz != null && clazz.isInstance(obj)) {
            return clazz.cast(obj);
        } else if (clazz != null) {
            throw new IllegalStateException();
        }

        return null;
    }

    @Override
    public List<X> findAll() {
        Set<String> keys = redisTemplate.keys(prefix + "*");

        if (Objects.isNull(keys) || keys.isEmpty()) {
            return Collections.emptyList();
        }

        List<Object> values = redisTemplate.opsForValue().multiGet(keys);

        if (Objects.isNull(values) || values.isEmpty()) {
            return Collections.emptyList();
        }

        values.forEach(obj -> {
            if (!clazz.isInstance(obj)) {
                throw new IllegalStateException();
            }
        });

        return values
                .stream()
                .map(clazz::cast)
                .toList();
    }

    @Override
    public void deleteById(long id) {
        redisTemplate.delete(prefix + id);
    }
}
