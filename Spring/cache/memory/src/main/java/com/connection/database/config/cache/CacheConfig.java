//package com.cache.memory.config.cache;
//
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//
//import java.time.Duration;
//
//@EnableCaching
//@Configuration
//public class CacheConfig extends CachingConfigurerSupport {
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        return RedisCacheManager
//                .builder(redisConnectionFactory)
//                .cacheDefaults(defaultCacheConfiguration())
//                .build();
//    }
//
//    private RedisCacheConfiguration defaultCacheConfiguration() {
//        return RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofSeconds(60))  // TTL for cache
//                .disableCachingNullValues();  // Do not cache null values
//    }
//
//}
