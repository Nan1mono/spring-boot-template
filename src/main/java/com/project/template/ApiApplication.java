package com.project.template;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
        log.info("swagger json url:{}", "http://localhost:8250/v3/api-docs");
        log.info("swagger doc.html url:{}", "http://localhost:8250/doc.html");
    }

}
