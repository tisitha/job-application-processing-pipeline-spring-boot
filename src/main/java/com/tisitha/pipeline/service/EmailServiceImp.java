package com.tisitha.pipeline.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImp implements EmailService {

    private final JavaMailSender javaMailSender;

    private final String email;

    public EmailServiceImp(JavaMailSender javaMailSender,@Value("${mail.organization.email}") String email) {
        this.javaMailSender = javaMailSender;
        this.email = email;
    }

    public void sendSimpleMessage(String applicantEmail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(applicantEmail);
        message.setFrom(email);
        message.setSubject("We have received your email application");
        message.setText("""
                We would like to acknowledge that we have received your email application.
                Our HR representative will review your application and send you a response shortly.
                Thank you for your patience.
                """);
        javaMailSender.send(message);
    }
}