package com.shgx.business.service;

/**
 * @Description
 * @auther guangxush
 * @create 2019/1/15
 */
public interface MailSendService {
    /**
     * 发送邮件
     * @param emailBody
     */
    public void sendEmail(String emailBody);
}
