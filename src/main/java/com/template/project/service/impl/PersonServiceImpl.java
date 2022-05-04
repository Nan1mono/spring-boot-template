package com.template.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.template.project.common.aop.AfterAnnotation;
import com.template.project.common.aop.BeginAnnotation;
import com.template.project.mapper.test1.PersonMapper;
import com.template.project.model.entity.Person;
import com.template.project.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {
    
    @Autowired
    private PersonMapper personMapper;

    @Override
    @BeginAnnotation
    @AfterAnnotation
    public Person getOnePerson() {
        Person person = personMapper.selectOne(null);
        return person;
    }
}
