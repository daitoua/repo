package com.daitou.o2o.service.impl;

import com.daitou.o2o.cache.JedisUtil;
import com.daitou.o2o.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private JedisUtil.Keys jdeisKeys;


    @Override
    public void removeFromCache(String keyPrefix) {

        Set<String> keys = jdeisKeys.keys(keyPrefix + "*");
        for (String key : keys) {
            jdeisKeys.delkeys(key);
        }

    }
}
