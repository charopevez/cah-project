package it.theboys.project0002api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    @Autowired
    private Environment env;
    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl sender=new JavaMailSenderImpl();
        sender.setHost(env.getProperty("spring.mail.host"));
        sender.setPort(Integer.valueOf(env.getProperty("spring.mail.port")));
        sender.setUsername(env.getProperty("spring.mail.username"));
        sender.setPassword(env.getProperty("spring.mail.password"));
        Properties properties = new Properties();
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.transport.protocol","smtp");
//        properties.put("mail.debug","true");
        sender.setJavaMailProperties(properties);
        return sender;
    }
}
