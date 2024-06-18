package com.example.api.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AppliedUserRepository {
    
    private final RedisTemplate<String,String> redisTemplate;
    
    public Long addCoupon(String userId){
        return redisTemplate.opsForSet().add(userId,"applied");
    }
    
}

