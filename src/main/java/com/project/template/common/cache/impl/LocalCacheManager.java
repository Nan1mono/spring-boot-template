package com.project.template.common.cache.impl;


import com.project.template.common.cache.CacheTemplate;
import com.project.template.common.helper.LocalCacheHelper;


public class LocalCacheManager extends CacheTemplate {

    @Override
    public void put(Object key, Object value) {
        LocalCacheHelper.put(key, value);
    }

    @Override
    public void putJSONStr(Object key, Object value) {
        LocalCacheHelper.putJSONStr(key, value);
    }

    @Override
    public Object get(Object key) {
        return LocalCacheHelper.get(key);
    }

    @Override
    public Object getIfPresent(Object key) {
        return LocalCacheHelper.getIfPresent(key);
    }

    @Override
    public void remove(Object key) {
        LocalCacheHelper.remove(key);
    }

    @Override
    public void remove(Iterable<?> keys) {
        LocalCacheHelper.remove(keys);
    }
}
