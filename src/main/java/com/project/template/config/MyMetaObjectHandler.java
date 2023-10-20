package com.project.template.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.project.template.common.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Value("${template.meta-handler.enable:true}")
    private boolean enable;

    @Value("${template.meta-handler.column:user_id}")
    private String column;

    @Override
    public void insertFill(MetaObject metaObject) {
        if (enable) {
            log.info("start insert fill ....");
            String userInfo = SecurityUtils.getUserDetail().getUserInfo(column);
            this.strictInsertFill(metaObject, "createBy", String.class, userInfo);
            this.strictInsertFill(metaObject, "updateBy", String.class, userInfo);
            this.strictInsertFill(metaObject, "createOn", LocalDateTime.class, LocalDateTime.now());
            this.strictInsertFill(metaObject, "updateOn", LocalDateTime.class, LocalDateTime.now());
        }
    }


    @Override
    public void updateFill(MetaObject metaObject) {
        if (enable) {
            log.info("start update fill ....");
            String userInfo = SecurityUtils.getUserDetail().getUserInfo(column);
            this.strictUpdateFill(metaObject, "updateOn", LocalDateTime.class, LocalDateTime.now());
            this.strictInsertFill(metaObject, "updateBy", String.class, userInfo);
        }
    }
}

