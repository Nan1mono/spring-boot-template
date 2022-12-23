package com.project.template.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.project.template.common.constant.Constants;
import com.project.template.common.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "createOn", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateOn", LocalDateTime.class, LocalDateTime.now());
    }


    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, "updateOn", LocalDateTime.class, LocalDateTime.now());
    }

    private Long getUser() {
        String userId;
        try {
            userId = HttpUtils.getRequestHeaderInfo(Constants.USER_ID);
            userId = (userId == null || "".equals(userId)) ? Constants.DEFAULT_USER_ID : userId;
        } catch (Exception e) {
            userId = Constants.DEFAULT_USER_ID;
        }
        return Long.parseLong(userId);
    }
}

