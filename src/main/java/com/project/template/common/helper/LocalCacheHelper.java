package com.project.template.common.helper;

import com.alibaba.fastjson2.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.project.template.common.exception.TempBusinessException;
import com.project.template.common.result.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class LocalCacheHelper {

    // cache的构造是一个隐式构造，需隐藏构造器
    private LocalCacheHelper() {

    }

    //缓存接口这里是LoadingCache，LoadingCache在缓存项不存在时可以自动加载缓存
    private static final Cache<Object, Object> cache = CacheBuilder.newBuilder()
            // 写缓存最大并发数
            .concurrencyLevel(8)
            // 过期时间
            .expireAfterWrite(7, TimeUnit.DAYS)
            // 设置缓存容器的初始容量为10
            .initialCapacity(10)
            // 设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
            .maximumSize(100)
            // 启用缓存信息统计
            .recordStats()
            // 设置缓存的移除通知
            .removalListener(notification -> log.warn("本地缓存" + notification.getKey() + "已失效，原因:" + notification.getCause()))
            // build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
            .build();


    /**
     * 直接存储
     *
     * @param key   key
     * @param value value
     */
    public static void put(Object key, Object value) {
        if (ObjectUtils.isEmpty(key)) {
            throw new TempBusinessException(ResultCodeEnum.DATA_ERROR);
        }
        cache.put(key, value);
    }

    /**
     * 序列化为JSON字符串存储
     *
     * @param key   key
     * @param value value
     */
    public static void putJSONStr(Object key, Object value) {
        if (ObjectUtils.isEmpty(key)) {
            throw new TempBusinessException(ResultCodeEnum.DATA_ERROR);
        }
        cache.put(key, JSON.toJSONString(value));
    }

    /**
     * 获取（如果不存在将存储一个null值）
     *
     * @param key key
     * @return {@link Object}
     */
    public static Object get(Object key) {
        if (ObjectUtils.isEmpty(key)) {
            throw new TempBusinessException(ResultCodeEnum.DATA_ERROR);
        }
        try {
            return cache.get(key, () -> "");
        } catch (ExecutionException e) {
            log.warn(e.getLocalizedMessage());
            throw new TempBusinessException(ResultCodeEnum.DATA_ERROR);
        }
    }

    /**
     * 获取并转化为指定对象
     *
     * @param key   key
     * @param clazz class
     * @return {@link T}
     */
    public static <T> T getParse(Object key, Class<T> clazz) {
        Object value = get(key);
        if (ObjectUtils.isEmpty(value) || StringUtils.isBlank(value.toString())) {
            return null;
        }
        return JSON.parseObject(value.toString(), clazz);
    }

    /**
     * 获取如果不存在不会进行任何处理
     *
     * @param key key
     * @return {@link Object}
     */
    public static Object getIfPresent(Object key) {
        return cache.getIfPresent(key);
    }

    /**
     * 获取并转换为指定对象
     * 如何不存在不会进行任何处理
     *
     * @param key   钥匙
     * @param clazz 克拉兹
     * @return {@link T}
     */
    public static <T> T getIfPresentParse(Object key, Class<T> clazz) {
        Object value = getIfPresent(key);
        assert value != null;
        return JSON.parseObject(value.toString(), clazz);
    }


    /**
     * 删除
     *
     * @param key key
     */
    public static void remove(Object key) {
        cache.invalidate(key);
    }

    /**
     * 批量删除
     *
     * @param keys keys
     */
    public static void remove(Iterable<?> keys) {
        cache.invalidateAll(keys);
    }

    /**
     * 全部删除
     */
    public static void removeAll() {
        cache.invalidateAll();
    }

}
