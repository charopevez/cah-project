package it.theboys.project0002api.service.websocket;

import it.theboys.project0002api.enums.chat.MessageStatus;
import it.theboys.project0002api.model.chat.ChatMessage;
import it.theboys.project0002api.repository.ChatRepository;
import it.theboys.project0002api.service.MailService;
import it.theboys.project0002api.storage.ChatStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static it.theboys.project0002api.utils.constants.EndPoints.*;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepo;
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private MailService mailService;

    @Override
    public void send(ChatMessage chatMessage) {
        var roomId = chatMessage.getTo();
        chatMessage.setTimestamp(Instant.now().toEpochMilli());
        // check if chatRoom exists
        var isRoomExists = ChatStorage.getInstance().getRoomList().contains(roomId);
        if (isRoomExists) {
            // save message to list
            if (roomId.equals("global")) {
                //save global chat to DB
                chatMessage.setStatus(MessageStatus.PUBLISHED);
                ChatMessage inDb= chatRepo.save(chatMessage);
            } else {
                //save to Storage
                chatMessage.setStatus(MessageStatus.PUBLISHED);
                ChatMessage inDb= chatRepo.save(chatMessage);
            }
                template.convertAndSend(CHAT_WS_OUTBOUND+roomId, chatMessage);
        } else {
            log.error(String.format("Room with id %s not found", roomId));
        }
    }

}
