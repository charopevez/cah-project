package it.theboys.project0002api.repository;

import it.theboys.project0002api.model.chat.ChatMessage;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends BaseRepository<ChatMessage, String> {
}
