package com.daitou.o2o.service;

public interface CacheService {

    /**
     * 根据传近来的前缀删除缓存
     * @param keyPrefix
     */
    void removeFromCache(String keyPrefix);


}
