package com.example.firsttask.service;
import com.example.firsttask.model.MailStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String fromMail;

    public void sendRegistrationEmail(String recipientEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromMail);
        message.setTo(recipientEmail);
        message.setSubject("Registration Successful");
        message.setText("Welcome! You have successfully registered.");
        mailSender.send(message);
    }

    public void sendLoginSuccessEmail(String recipientEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromMail);
        message.setTo(recipientEmail);
        message.setSubject("Login Successful");
        message.setText("You have successfully logged in.");
        mailSender.send(message);
    }
}
