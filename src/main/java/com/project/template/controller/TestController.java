package com.project.template.controller;

import com.project.template.common.result.Result;
import com.project.template.common.util.MailUtils;
import com.project.template.model.bo.MailRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private MailUtils mailUtils;
    
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
    
}
