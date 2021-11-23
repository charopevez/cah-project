package it.theboys.project0002api.service.websocket;

import it.theboys.project0002api.model.chat.ChatMessage;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ChatService {

    void send(ChatMessage chatMessage);

}
