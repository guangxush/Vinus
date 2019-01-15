package com.shgx.business.service.impl;

import com.shgx.business.config.MailConfig;
import com.shgx.business.service.MailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * @Description
 * @auther guangxush
 * @create 2019/1/15
 */
@Service
@PropertySource("classpath:config/email.properties")
public class MailSendServiceImpl implements MailSendService {

    @Value("${mail.subject}")
    private String mailSubject;

    @Value("${mail.from}")
    private String mailFrom;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.to}")
    private String mailTo;

    @Autowired
    private MailConfig mailConfig;

    @Override
    public void sendEmail(String emailBody) {
        try{
            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                @Override
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                    message.setSubject(mailSubject);
                    message.setFrom(mailFrom);
                    message.setTo(mailTo);
                    message.setText(emailBody);
                }
            };
            JavaMailSenderImpl javaMailSender = mailConfig.getMailSender();
            javaMailSender.send(preparator);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
