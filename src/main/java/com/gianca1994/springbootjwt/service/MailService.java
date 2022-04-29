package com.gianca1994.springbootjwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String EMAIL_SENDER;

    public void sendMail(String to, String subject, String username){
        SimpleMailMessage message = new SimpleMailMessage();

        String textEmail = "Welcome " + username + " to NAME_PROJECT!";

        message.setFrom(EMAIL_SENDER);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(textEmail);

        javaMailSender.send(message);

    }
}
