package it.theboys.project0002api.service;

import it.theboys.project0002api.enums.db.GameName;
import it.theboys.project0002api.exception.LobbyException;
import it.theboys.project0002api.model.Lobby;

import java.util.List;

public interface LobbyService {
    List<Lobby> getLobbyList();
    Lobby getLobby(GameName gameName);
    Lobby joinLobby(GameName gameName, String userId) throws LobbyException;

    Lobby leaveLobby(GameName gameName, String userId) throws LobbyException;
}
