package it.theboys.project0002api.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public interface MailService {
    @Async
    void sendTo(String emailTo, String subject, String message);
}
