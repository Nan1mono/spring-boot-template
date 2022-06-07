package com.template.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.template.project.mapper.dataSource1.PersonMapper;
import com.template.project.model.entity.Person;
import com.template.project.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {
    
    @Autowired
    private PersonMapper personMapper;

    @Override
    public Person getOnePerson() {
        LambdaQueryWrapper<Person> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Person::getPersonId,1);
        Person person = personMapper.selectOne(lambdaQueryWrapper);
        return person;
    }
}
