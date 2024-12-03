package com.df.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisIdempotencyService {

    private static final String IDEMPOTENCY_KEY_PREFIX = "idempotency:";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public boolean isIdempotent(String key, long expirationSeconds) {
        Boolean isSet = redisTemplate.opsForValue().setIfAbsent(key, "1", expirationSeconds, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(isSet);
    }

    public String generateKey(List<Object> obj) {
        try {
            String jsonString = obj.toString(); // Customize this if needed for your object structure
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(jsonString.getBytes(StandardCharsets.UTF_8));
            return IDEMPOTENCY_KEY_PREFIX + bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating hash for idempotency key", e);
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
