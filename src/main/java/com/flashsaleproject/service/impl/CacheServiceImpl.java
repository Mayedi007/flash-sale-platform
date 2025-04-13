package com.flashsaleproject.service.impl;

import com.flashsaleproject.service.CacheService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of local in-memory caching using Google Guava.
 * Used for faster retrieval before querying Redis or database.
 *
 * Author: Mohamed Ayadi
 * GitHub: https://github.com/Mayedi007
 * Date: 2025-04-13
 */

@Service
public class CacheServiceImpl implements CacheService {

    private Cache<String, Object> commonCache;

    /**
     * Initialize the local cache on startup.
     */
    @PostConstruct
    public void init() {
        commonCache = CacheBuilder.newBuilder()
                .initialCapacity(10)             // Initial capacity of 10 entries
                .maximumSize(100)                // Maximum of 100 entries
                .expireAfterWrite(60, TimeUnit.SECONDS) // Expire after 60 seconds
                .build();
    }

    /**
     * Store a value in the cache with the given key.
     */
    @Override
    public void setCommonCache(String key, Object value) {
        commonCache.put(key, value);
    }

    /**
     * Retrieve a value from the cache by key.
     */
    @Override
    public Object getFromCommonCache(String key) {
        return commonCache.getIfPresent(key);
    }
}
