package com.project.template.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanUtils {

    private final ApplicationContext context;

    @Autowired
    public SpringBeanUtils(ApplicationContext applicationContext) {
        this.context = applicationContext;
    }

    public <E> E getBean(Class<E> clazz) {
        return context.getBean(clazz);
    }

}
