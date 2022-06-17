package com.template.project.controller;

import com.template.project.common.result.Result;
import com.template.project.common.utils.MailUtils;
import com.template.project.model.bo.MailRecord;
import com.template.project.model.entity.Person;
import com.template.project.model.entity.User;
import com.template.project.service.PersonService;
import com.template.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

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
        return mailRecord.getTo();
    }
    
}
