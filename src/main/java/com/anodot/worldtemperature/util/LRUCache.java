package com.anodot.worldtemperature.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * An implementation of the Least Recently Used (LRU) cache with time-to-live (TTL) eviction policy.
 *
 * @param <K> The type of keys in the cache.
 * @param <V> The type of values in the cache.
 */
@Slf4j
public class LRUCache<K, V> {

    private final Map<K, CacheEntry<V>> cache;
    private final int capacity;
    private final long ttlMillis;
    private final Lock lock = new ReentrantLock(); // Lock for cache read/write synchronization

    /**
     * Constructs a new LRUCache with the specified capacity and time-to-live (TTL) for entries.
     *
     * @param capacity   The maximum number of entries the cache can hold.
     * @param ttl        The time-to-live (TTL) for entries in seconds.
     */
    public LRUCache(int capacity, long ttl) {
        this.capacity = capacity;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        this.ttlMillis = timeUnit.toMillis(ttl);
        this.cache = getCache(capacity);
    }

    /**
     * Retrieves the value associated with the specified key from the cache.
     *
     * @param key The key whose associated value is to be retrieved.
     * @return The value associated with the specified key, or {@code null} if the key is not present or the entry has expired.
     */
    public V get(K key) {
        lock.lock(); // Acquire the lock
        try {
            CacheEntry<V> entry = cache.get(key);
            if (entry != null && !entry.isExpired()) {
                log.debug("returned cache with key {}.", key);
                return entry.getValue();
            } else {
                if (entry == null) {
                    log.debug("the key {} is not in cache, nothing to return.", key);
                } else if (entry.isExpired()) {
                    log.debug("cached key {} removed due to expiration, nothing to return.", key);
                    cache.remove(key);
                }
                return null;
            }
        } finally {
            lock.unlock(); // Release the lock
        }
    }

    /**
     * Associates the specified value with the specified key in the cache.
     *
     * @param key   The key with which the specified value is to be associated.
     * @param value The value to be associated with the specified key.
     */
    public void put(K key, V value) {
        lock.lock(); // Acquire the lock
        try {
            CacheEntry cacheEntry = new CacheEntry<>(value, ttlMillis);
            cache.put(key, cacheEntry);
            log.debug("Cache populated with key {}", key);
        } finally {
            lock.unlock(); // Release the lock
        }
    }

    /**
     * Constructs and returns a LinkedHashMap instance with the specified capacity
     * and a custom removeEldestEntry method to enforce the LRU eviction policy.
     *
     * @param capacity The maximum number of entries the cache can hold.
     * @return A LinkedHashMap instance with the specified capacity and LRU eviction policy.
     */
    private Map<K, CacheEntry<V>> getCache(int capacity) {
        // Initialize a LinkedHashMap with the specified capacity and load factor,
        // and enable access-ordering to support the LRU eviction policy.
        return new LinkedHashMap<>(capacity, 0.75f, true) {
            /**
             * Determines whether the eldest entry should be removed from the cache.
             * This method is invoked by put and putAll after inserting a new entry.
             *
             * @param eldest The eldest entry in the cache.
             * @return {@code true} if the eldest entry should be removed, otherwise {@code false}.
             */
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, CacheEntry<V>> eldest) {
                // Check if the current size of the cache exceeds the maximum capacity.
                // If true, the eldest entry will be removed to maintain the capacity limit.
                return size() > LRUCache.this.capacity;
            }
        };
    }

    // Inner class representing cache entries with expiration time
    private static class CacheEntry<V> {
        @Getter
        private final V value;
        private final long creationTime;
        private final long ttlMillis;

        public CacheEntry(V value, long ttlMillis) {
            this.value = value;
            this.creationTime = System.currentTimeMillis();
            this.ttlMillis = ttlMillis;
        }

        public boolean isExpired() {
            return System.currentTimeMillis() - creationTime > ttlMillis;
        }
    }
}

