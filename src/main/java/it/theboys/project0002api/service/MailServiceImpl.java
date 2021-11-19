package it.theboys.project0002api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private Environment env;

    @Async
    public void sendTo(String emailTo, String subject, String message){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(env.getProperty("spring.mail.host"));
        mail.setTo(emailTo);
        mail.setSubject(subject);
        mail.setText(message);

        mailSender.send(mail);
    }
}
