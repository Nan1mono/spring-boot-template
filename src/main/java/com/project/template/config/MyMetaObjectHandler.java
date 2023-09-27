package com.project.template.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Value("${template.meta-handler.enable}")
    private boolean enable;

    @Value("${template.meta-handler.column}")
    private String column;

    @Override
    public void insertFill(MetaObject metaObject) {
        if (enable) {
            log.info("start insert fill ....");
            this.strictInsertFill(metaObject, "createBy", String.class, column);
            this.strictInsertFill(metaObject, "updateBy", String.class, column);
            this.strictInsertFill(metaObject, "createOn", LocalDateTime.class, LocalDateTime.now());
            this.strictInsertFill(metaObject, "updateOn", LocalDateTime.class, LocalDateTime.now());
        }
    }


    @Override
    public void updateFill(MetaObject metaObject) {
        if (enable) {
            log.info("start update fill ....");
            this.strictUpdateFill(metaObject, "updateOn", LocalDateTime.class, LocalDateTime.now());
            this.strictInsertFill(metaObject, "updateBy", String.class, column);
        }
    }
}

