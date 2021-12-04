package it.theboys.project0002api.controller;

import it.theboys.project0002api.model.chat.ChatMessage;
import it.theboys.project0002api.service.websocket.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

import static it.theboys.project0002api.utils.constants.EndPoints.CHAT_WS;
import static it.theboys.project0002api.utils.constants.EndPoints.CHAT_WS_OUTBOUND;


@Controller
@RestController
@Slf4j
public class ChatController {
    @Autowired
    private ChatService chatService;

//    @MessageMapping(CHAT_WS_INBOUND+"/{roomId}")
//    public void sendMessage(@DestinationVariable String roomId, @Payload ChatMessage chatMessage, Principal principal) {
//        // ToDo error handler
//        log.debug(roomId);
//        log.debug(String.valueOf(principal));
//        chatService.send(chatMessage);
//    }
    @MessageMapping(CHAT_WS_OUTBOUND+"{roomId}")
//    @SendTo("/chat/global")
        public void sendTestMessage(@DestinationVariable String roomId, @Payload ChatMessage chatMessage) {
        // ToDo error handler
        log.info(roomId);
        log.info(String.valueOf(chatService));
        chatService.send(chatMessage);
    }

}
