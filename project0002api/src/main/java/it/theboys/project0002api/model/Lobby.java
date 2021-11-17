package it.theboys.project0002api.model;

import it.theboys.project0002api.enums.GameName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Lobby {
    private GameName lobbyId;
    private String lobbyName;
    private List<String> playerList;
    private List<String> gameList;

    public Lobby(GameName gameName){
        lobbyId=gameName;
        lobbyName= gameName.getDesc();
        playerList = new ArrayList<>();
        gameList = new ArrayList<>();
    }

    public void addPlayer(String player){
        playerList.add(player);
    }
    public void removePlayer(String player){
        playerList.remove(player);
    }

    public void addServer(String server){
        playerList.add(server);
    }
    public void removeServer(String server){
        playerList.add(server);
    }
}
