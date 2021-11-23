package it.theboys.project0002api.model.chat;

import it.theboys.project0002api.enums.chat.MessageStatus;
import it.theboys.project0002api.model.database.User;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ChatMessage {
    @Id
    private String id;
    private String roomId;
    private String from;
    private String to;
    private String content;
    private long timestamp;
    private MessageStatus status;
}
