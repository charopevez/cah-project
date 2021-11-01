package it.theboys.project0002api.service.cardgame;


public interface GameMainframeService {

    public String launchGameServer(String user, String settings);

    public String joinGameServer(String user ,String password, String gameServerId);

    public String leaveGameServer(String user, String gameServerId);

    public String terminateGameServer(String user, String gameServerId);

    public String messageToServer(String user, String gameServerId);

}
