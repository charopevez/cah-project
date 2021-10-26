package it.theboys.project0002api.model.base;

import lombok.Data;

import java.util.Map;

@Data
public class Lobby {
    private String lobbyId;
    private Map<String, User> userList;
    private String lobbyImage;
    private String lobbyName;

    public void setUser(User user) {
        userList.put(user.getUserName(), user);
    }
}
