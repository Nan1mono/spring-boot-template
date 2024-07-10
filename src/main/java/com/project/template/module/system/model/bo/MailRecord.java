package com.project.template.module.system.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于邮箱工具类
 * 包含：
 * 1.发送人地址
 * 2.收件人地址
 * 3.发件信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailRecord {
    private String from;
    private String to;
    private String message;
}
