package com.template.project.controller;

import com.template.project.common.exception.MyException;
import com.template.project.common.result.Result;
import com.template.project.common.result.ResultCodeEnum;
import com.template.project.common.util.MailUtils;
import com.template.project.model.bo.MailRecord;
import com.template.project.model.entity.Person;
import com.template.project.model.entity.User;
import com.template.project.service.PersonService;
import com.template.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private PersonService personService;
    
    @Autowired
    private MailUtils mailUtils;
    
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
    
    @GetMapping("/send")
    public void send(){
        mailUtils.send("1174752357@qq.com");
    }
    
    @GetMapping("/send2/{email}")
    public String send2(@PathVariable String email){
        MailRecord mailRecord = mailUtils.send(email);
        mailRecord.getMessage();
        mailRecord.getFrom();
        mailRecord.getTo();
        return mailRecord.getTo();
    }
    
    @GetMapping("/insert")
    @Transactional(transactionManager = "transactionManager2",rollbackFor = {Exception.class, MyException.class})
    public String insert(){
        User user = new User();
        user.setAge(10);
        userService.save(user);
//        int i = 1/0;
        throw new MyException(ResultCodeEnum.CODE_ERROR);
//        return "OK";
    }
    
}
