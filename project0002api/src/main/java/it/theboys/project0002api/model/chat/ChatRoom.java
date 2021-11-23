package it.theboys.project0002api.model.chat;

import it.theboys.project0002api.model.database.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class ChatRoom {
    private String roomId;
    private String roomName;
    private String roomImage;
    private List<User> roomGuestList = new ArrayList<>();
    private List<ChatMessage> messageList = new ArrayList<>();

    private boolean isPrivate=false;

    public ChatRoom() {
        roomId="global";
        roomName="Global chat";
        roomImage=String.format( "https://itboys-project0002.s3.ap-northeast-1.amazonaws.com/img/room/%s.jpg",roomId.toLowerCase());
    }
    public ChatRoom(String roomName, String roomImage) {
        roomId= UUID.randomUUID().toString();
        this.roomName=roomName;
        this.roomImage=roomImage;
    }
    public ChatRoom(String roomName, String roomImage, List<User> allowedList) {
        roomId= UUID.randomUUID().toString();
        this.roomName=roomName;
        this.roomImage=roomImage;
        roomGuestList=allowedList;
    }
}
