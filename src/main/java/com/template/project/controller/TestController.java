package com.template.project.controller;

import com.template.project.common.result.Result;
import com.template.project.model.entity.Person;
import com.template.project.model.entity.User;
import com.template.project.service.PersonService;
import com.template.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private PersonService personService;
    
    @GetMapping("/test1")
    public Result test1(){
        Person one = personService.getOnePerson();
        return Result.ok(one);
    }
    
    @GetMapping("/test2")
    public Result test2(){
        User one = userService.getOne(null);
        return Result.ok(one);
    }
}
