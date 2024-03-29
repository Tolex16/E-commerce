package com.example.ecommerce.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailService {

   @Autowired
    private final JavaMailSender javaMailSender;

   @Bean
    public void sendPasswordMail(String to,String username, String password) {
        String subject = "Welcome to To-pro";
        String body = "Hi " + username + "\n This is your secure generated otp:" + password;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
    }
}
