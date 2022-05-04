package com.template.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.template.project.model.entity.Person;
import org.springframework.stereotype.Service;

public interface PersonService extends IService<Person> {

    Person getOnePerson();
}
