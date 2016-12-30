package com.eloan.business.service.impl;

import com.eloan.base.Exception.LogicException;
import com.eloan.business.domain.MyMailMessage;
import com.eloan.business.service.IMailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author Dingying
 * @version 1.0
 * @create 2016-12-30 下午 3:17
 **/
@Service
public class MailServiceImpl implements IMailService {

    @Value("${mail.host}")
    private String host;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    @Override
    public void sendMail(MyMailMessage mail) {
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        // 设定mail server
        senderImpl.setHost(host);
        // 建立邮件消息,发送简单邮件和html 邮件的区别
        MimeMessage mailMessage = senderImpl.createMimeMessage();
        MimeMessageHelper messageHelper = new
                MimeMessageHelper(mailMessage, "UTF-8");
        try {
            // 设置收件人，寄件人
            messageHelper.setTo(mail.getToAddress());
            messageHelper.setFrom(username);
            messageHelper.setSubject(mail.getTitle());
            // true 表示启动HTML 格式的邮件
            messageHelper.setText(mail.getContent(), true);
            // 根据自己的情况,设置username
            senderImpl.setUsername(username);
            senderImpl.setPassword(password);
            Properties prop = new Properties();
            prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证
            prop.put("mail.smtp.timeout", "25000");
            senderImpl.setJavaMailProperties(prop);
            // 发送邮件
            senderImpl.send(mailMessage);
            System.out.println(mail.getContent());
        } catch (Exception e) {
            e.printStackTrace();
            throw new LogicException("发送邮件失败!");
        }
    }
}
