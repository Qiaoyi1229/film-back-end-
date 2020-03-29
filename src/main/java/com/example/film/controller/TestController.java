package com.example.film.controller;

import com.example.film.utils.ImageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author 陈乐
 * @version 1.0
 * @date 2020/3/11 16:55
 */
@Controller
public class TestController {

    @Autowired
    JavaMailSender mailSender;

    @GetMapping(value = "/index")
    public String file() {
        return "HelloWord";
    }

    @PostMapping(value = "/fileUpload")
    public String fileUpload(@RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request) {
        String filename = ImageUpload.upload(file);
        model.addAttribute("filename", filename);
        return "HelloWord";
    }

    @RequestMapping("/send")
    public String sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("cl1429745331@163.com");
        message.setTo("1429745331@qq.com");
        message.setSubject("it is a test for spring boot");
        message.setText("你好，我是小黄，我正在测试发送邮件。");
        try {
            mailSender.send(message);
            System.out.println("发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "HelloWord";
    }

    /**
     * 带附件发送，可多个附件 图片，doc都可以发送。
     */
    @GetMapping("/sendFile")
    public String sendAttachmentsMail() {

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("cl1429745331@163.com");
            helper.setTo("1429745331@qq.com");
            helper.setSubject("购票成功");
            helper.setText("购票成功");

            FileSystemResource file = new FileSystemResource("F:/ticket.pdf");
            helper.addAttachment("电影票.pdf",file);
            mailSender.send(message);
            System.out.println("带附件的邮件发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发送带附件的邮件失败");
        }
        return "HelloWord";
    }

}
