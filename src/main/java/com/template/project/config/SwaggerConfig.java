package com.template.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket getDocket(){
        // 指定文档风格
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        // 封面内容
        apiInfoBuilder.title("template接口说明").description("文档说明了template接口说明后端接口规范").version("2.0.1").contact(new Contact("Nanimono","www.nanimono.com","nan1mono@163.com"));
        ApiInfo apiInfo = apiInfoBuilder.build();
        // 设定需要生成的接口
        docket.apiInfo(apiInfo).select().apis(RequestHandlerSelectors.basePackage("com.template.project.controller")).paths(PathSelectors.any()).build();
        return docket;
    }
}
