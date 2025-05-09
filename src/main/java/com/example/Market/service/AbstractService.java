package com.example.Market.service;

import com.example.Market.Expetion.EntityNotFoundException;
import com.example.Market.repository.redis.RedisRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

public abstract class AbstractService<X> implements ServiceContract<X> {
    private final JpaRepository<X, Long> jpaRepository;
    private final RedisRepository<X> redisRepository;
    private final Class<X> entityClass;

   protected AbstractService(JpaRepository<X, Long> jpaRepository,
                             RedisRepository<X> redisRepository,
                             Class<X> entityClass) {
       this.jpaRepository = jpaRepository;
       this.redisRepository = redisRepository;
       this.entityClass = entityClass;
   }

    private Long getEntityId(X entity) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {

        Method method = entityClass.getMethod("getId");

        return (Long) method.invoke(entity);
    }

    @Override
    public void save(X x) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
       jpaRepository.save(x);

       redisRepository.deleteById(getEntityId(x));
    }

    @Override
    public X findById(long id)throws EntityNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
       X entity = redisRepository.getById(id);

       if (Objects.nonNull(entity)) {
           return entity;
        }

       entity = jpaRepository.findById(id).orElseThrow(EntityNotFoundException::new);

       redisRepository.save(entity, getEntityId(entity));

       return entity;
    }
    @Override
    public List<X> findAll() {return jpaRepository.findAll();}

    @Override
    public void deleteById(long id) {
        if (!jpaRepository.existsById(id)) {
            throw new RuntimeException("Not found");
        }
        jpaRepository.deleteById(id);

        redisRepository.deleteById(id);
    }


}
