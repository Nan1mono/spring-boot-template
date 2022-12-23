package com.project.template;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.project.template.model.base.entity.BaseEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * 代码生成器
 *
 */
public class Generator {

    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig.Builder(
            "jdbc:mysql://localhost:3307/test1?useSSL=false&serverTimezone=GMT%2B8",
            "root",
            "123456");

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        Map<OutputFile, String> map = new HashMap<>(5);
        map.put(OutputFile.xml, projectPath + "/src/main/resources/mapper");
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author("lee")
                        .outputDir(projectPath + "/src/main/java")
                        .disableOpenDir()
                        .enableSwagger())
                // 包配置
                .packageConfig((scanner, builder) -> builder
                        .parent("com.project.template")
                        .service("service")
                        .serviceImpl("service.impl")
                        .mapper("mapper")
                        .controller("controller")
                        .entity("model.entity")
                        .pathInfo(map)
                )
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply(
                                "请输入表名，多个英文逗号分隔？所有输入 all")))
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .controllerBuilder()
                        .enableRestStyle()
                        .entityBuilder()
                        .disableSerialVersionUID()
                        .enableLombok()
                        .superClass(BaseEntity.class)
                        .addSuperEntityColumns(
                                "is_delete",
                                "create_on",
                                "update_on")
                        .enableTableFieldAnnotation()
                        .mapperBuilder()
                        .mapperAnnotation(Repository.class)
                        .build())
                .execute();
    }

    /** 处理 all 情况 */
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
