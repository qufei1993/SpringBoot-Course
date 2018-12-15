package com.angelo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import java.lang.reflect.Array;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Resource
    TemplateEngine templateEngine;

    String to = "your163account@163.com";

    @Test
    public void sendTextEmailTest() {
        mailService.sendTextMail(to, "发送文本邮件", "hello，这是Spring Boot发送的一封文本邮件!");
    }

    @Test
    public void sendHtmlEmailTest() throws MessagingException {
        String content = "<html>" +
                "<body>" +
                    "<h1 style=\"" + "color:red;" + "\">hello，这是Spring Boot发送的一封HTML邮件</h1>" +
                "</body></html>";

        mailService.sendHtmlMail(to, "发送HTML邮件", content);
    }

    @Test
    public void sendAttachmentEmailTest() throws MessagingException {
        String[] filePathList = new String[2];
        filePathList[0] = "/Users/qufei/Documents/study/SpringBoot-WebApi/chapter4.zip";
        filePathList[1] = "/Users/qufei/Documents/study/SpringBoot-WebApi/chapter5.zip";

        mailService.sendAttachmentMail(to, "发送附件邮件", "hello，这是Spring Boot发送的一封附件邮件!", filePathList);
    }

    @Test
    public void sendHtmlInlinePhotoMailTest() throws MessagingException {
        String srcPath = "/Users/qufei/Documents/study/SpringBoot-WebApi/chapter6/img/pic18.jpg";
        String srcId = "pic18";
        String content = "<html>" +
                "<body>" +
                "<h2>hello，这是Spring Boot发送的一封HTML内嵌图片的邮件</h2>" +
                "<img src=\'cid:"+ srcId +"\'></img>" +
                "</body></html>";

        mailService.sendHtmlInlinePhotoMail(to, "发送图片邮件", content, srcPath, srcId);
    }

    @Test
    public void testTemplateEmailTest() throws MessagingException {
        Context context = new Context();
        context.setVariable("username", "张三");
        context.setVariable("id", "2667395");

        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail(to, "发送模板邮件", emailContent);
    }
}