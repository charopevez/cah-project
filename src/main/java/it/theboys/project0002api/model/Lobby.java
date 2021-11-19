package it.theboys.project0002api.model;

import it.theboys.project0002api.enums.GameName;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class Lobby {
    private GameName lobbyId;
    private String lobbyName;
    private String lobbyImg;
    private List<String> playerList;
    private List<String> gameList;

    public Lobby(GameName gameName){
        lobbyId=gameName;
        lobbyName= gameName.getDesc();
        lobbyImg=String.format( "https://itboys-project0002.s3.ap-northeast-1.amazonaws.com/img/lobby/%s.jpg",lobbyId.toString().toLowerCase());
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
