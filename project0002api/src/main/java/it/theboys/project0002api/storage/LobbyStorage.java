package it.theboys.project0002api.storage;

import it.theboys.project0002api.model.base.Lobby;

import java.util.HashMap;
import java.util.Map;

public class LobbyStorage {
    private static Map<String, Lobby> lobbyList;
    private static LobbyStorage instance;

    private LobbyStorage() {
        lobbyList = new HashMap<>();
    }

    public static synchronized LobbyStorage getInstance() {
        if (instance == null) {
            instance = new LobbyStorage();
        }
        return instance;
    }

    public Map<String, Lobby> getLobbyList() {
        return lobbyList;
    }

    public void setLobby(Lobby lobby) {
        lobbyList.put(lobby.getLobbyId(), lobby);
    }
}
