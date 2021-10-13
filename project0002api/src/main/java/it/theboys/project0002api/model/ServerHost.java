package it.theboys.project0002api.model;

import lombok.Data;

@Data
public class ServerHost {
    private String serverId;
    private String serverName;
    private String serverHostUsername;
    private ServerStatus serverStatus;
<<<<<<< HEAD
=======

    public void setServerId(String uuid){
        this.serverId = uuid;
    }
    public String getServerId(){
        return serverId;
    }
    public void setServerName(String name){
        this.serverName = name;
    }
    public String getServerName(){
        return serverName;
    }
    public void setServerHostUsername(String hostUsername){
        this.serverHostUsername = hostUsername;
    }
    public String getServerHostUsername(){
        return serverHostUsername;
    }
    public void setServerStatus(ServerStatus serverStatus){
        this.serverStatus = serverStatus;
    }

>>>>>>> 19730a82f87bb3c16e2a7363e4cbf51ae4468e67
}
