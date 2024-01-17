package com.project.template.common.cache;

import com.project.template.common.cache.impl.LocalCacheManager;
import com.project.template.common.cache.impl.RedisCacheManager;
import com.project.template.common.util.RedisUtils;
import com.project.template.common.util.SpringBeanUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Qualifier("cacheTemplateManager")
@Slf4j
public class CacheTemplateManager {

    @Resource
    private SpringBeanUtils springBeanUtils;

    @Value("${template.cache.configuration.type:local}")
    private String type;

    private CacheTemplate cacheTemplate;

    public CacheTemplate createManager() {
        if (this.cacheTemplate != null) {
            return cacheTemplate;
        }
        log.info("当前启动的缓存类型为：{}", type);
        if ("redis".equals(type)) {
            this.cacheTemplate = new RedisCacheManager(springBeanUtils.getBean(RedisUtils.class));
        } else if ("local".equals(type)) {
            this.cacheTemplate = new LocalCacheManager();
        } else {
            throw new IllegalArgumentException("Unsupported cache manager type");
        }
        return this.cacheTemplate;
    }

}
