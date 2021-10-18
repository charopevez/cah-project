package it.theboys.project0002api.model;

import lombok.Data;
import java.util.*;

@Data
public class GameServer {
    
    private String serverHost;
    private String currentQuestion;
    private String gameServer;
    private String serverId;
    private String serverName;
    private String settings;
    
    private int currentRound;
    private int currentDealer;

    public void setServerHost(String Host){
        this.serverHost = Host;
    }
    public String getServerHost(){
        return serverHost;
    }

    public void setCurrentQuestion(String CardQuestion){
        this.currentQuestion = CardQuestion;
    }
    public String getCurrentQuestion(){
        return currentQuestion;
    }

    public void setGameServer(String WebSocketServer){
        this.gameServer = WebSocketServer;
    }
    public String getGameServer(){
        return gameServer;
    }

    public void setSettings(String settings){
        this.settings = settings;
    }
    public String getSettings(){
        return settings;
    }

    public void setServerId(String uuid){
        this.serverId = uuid;
    }
    public String getServerId(){
        return serverId;
    }

    public void setServerName(String serverName){
        this.serverName = serverName;
    }
    public String getServerName(){
        return serverName;
    }

    public void setCurrentRound(int round){
        this.currentRound = round;
    }
    public int getCurrentrRound(){
        return currentRound;
    }

    public void setCurrentDealer(int dealer){
        this.currentDealer = dealer;
    }
    public int getCurrentDealer(){
        return currentDealer;
    }

    Map<Integer, String> map = new HashMap<>();

    public void getHand(){
        Player player = new Player();
    }

    public void start() throws Exception{
        GameLobby gamelobby = new GameLobby();
        int numPlayers = gamelobby.players.size();
        if(numPlayers >= 3){
            currentDealer = (int)(Math.random() * numPlayers);

            //カードセットの処理
        }else{
            throw new Exception("プレイヤーがゲームの最低要件を満たしていません。");
        }
    }
}
