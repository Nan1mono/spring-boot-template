package com.project.template.common.cache.impl;

import com.alibaba.fastjson2.JSON;
import com.project.template.common.cache.CacheTemplate;
import com.project.template.common.util.RedisUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RedisCacheManager extends CacheTemplate {

    private final RedisUtils redisUtils;

    /**
     * 构造器注入RedisUtils
     *
     * @param redisUtils 缓存工具
     */
    @Autowired
    public RedisCacheManager(RedisUtils redisUtils) {
        super();
        this.redisUtils = redisUtils;
    }

    @Override
    public void put(Object key, Object value) {
        redisUtils.set(key.toString(), value);
    }

    @Override
    public void putJSONStr(Object key, Object value) {
        redisUtils.set(key.toString(), JSON.toJSONString(value));
    }

    @Override
    public Object get(Object key) {
        Object object = redisUtils.get(key.toString());
        if (ObjectUtils.isEmpty(object)) {
            return "";
        }
        return object;
    }

    @Override
    public Object getIfPresent(Object key) {
        return redisUtils.get(key.toString());
    }

    @Override
    public void remove(Object key) {
        redisUtils.del(key.toString());
    }

    @Override
    public void remove(Iterable<?> keys) {
        List<String> collection = Lists.newArrayList();
        keys.forEach(t -> collection.add(t.toString()));
        redisUtils.batchDelete(collection);
    }
}
