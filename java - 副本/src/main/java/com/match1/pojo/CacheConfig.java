package com.match1.pojo;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        // 配置 CacheManager 实例
        return new ConcurrentMapCacheManager("cacheName");
    }

    @Bean
    public Cache cache(CacheManager cacheManager) {
        return cacheManager.getCache("cacheName");
    }
}