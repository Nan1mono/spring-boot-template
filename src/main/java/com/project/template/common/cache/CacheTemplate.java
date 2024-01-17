package com.project.template.common.cache;

import com.alibaba.fastjson2.JSON;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("cacheTemplate")
public abstract class CacheTemplate {

    public abstract void put(Object key, Object value);

    /**
     * 序列化为JSON字符串存储
     *
     * @param key   key
     * @param value value
     */
    public abstract void putJSONStr(Object key, Object value);

    /**
     * 获取（如果不存在将存储一个null值）
     *
     * @param key key
     * @return {@link Object}
     */
    public abstract Object get(Object key);

    /**
     * 获取并转化为指定对象
     *
     * @param key   key
     * @param clazz class
     * @return {@link T}
     */
    public <T> T getParse(Object key, Class<T> clazz) {
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
    public abstract Object getIfPresent(Object key);

    /**
     * 获取并转换为指定对象
     * 如何不存在不会进行任何处理
     *
     * @param key   钥匙
     * @param clazz 克拉兹
     * @return {@link T}
     */
    public <T> T getIfPresentParse(Object key, Class<T> clazz) {
        Object value = getIfPresent(key);
        assert value != null;
        return JSON.parseObject(value.toString(), clazz);
    }


    public abstract void remove(Object key);

    public abstract void remove(Iterable<?> keys);

}
