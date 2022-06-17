package com.template.project.common.utils;


import com.template.project.common.exception.MyException;
import com.template.project.common.result.ResultCodeEnum;
import com.template.project.model.bo.MailRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

/**
 * 邮箱验证工具
 */
@Component
public class MailUtils {
    
    @Resource
    private JavaMailSender mailSender;
    
    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送成功会返回验证码
     * @param toMail
     */
    public MailRecord send(String toMail) {

        int count = 1;//默认发送一次
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MailRecord mailRecord = new MailRecord();
        mailRecord.setFrom(from);
        mailRecord.setTo(toMail);
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            while (count-- != 0) {
                String codeNum = "";
                int[] code = new int[3];
                Random random = new Random();
                //自动生成验证码
                for (int i = 0; i < 6; i++) {
                    int num = random.nextInt(10) + 48;
                    int uppercase = random.nextInt(26) + 65;
                    int lowercase = random.nextInt(26) + 97;
                    code[0] = num;
                    code[1] = uppercase;
                    code[2] = lowercase;
                    codeNum += (char) code[random.nextInt(3)];
                }
                System.out.println(codeNum);
                //标题
                helper.setSubject("您的验证码为：" + codeNum);
                //内容
                helper.setText("您好!。您的验证码为：" + "<h2>" + codeNum + "</h2>" + "千万不能告诉别人哦！", true);
                //邮件接收者
                helper.setTo(toMail);
                //邮件发送者，必须和配置文件里的一样，不然授权码匹配不上
                helper.setFrom(from);
                mailSender.send(mimeMessage);
                System.out.println("邮件发送成功！" + (count + 1));
                mailRecord.setMessage(codeNum);
            }
        } catch (MessagingException e){
            e.printStackTrace();
            throw new MyException(ResultCodeEnum.SERVICE_ERROR);
        }
        return mailRecord;
    }
}
