package it.theboys.project0002api.service;

import it.theboys.project0002api.enums.GameName;
import it.theboys.project0002api.exception.LobbyException;
import it.theboys.project0002api.model.Lobby;

import java.util.List;
import java.util.Map;

public interface LobbyService {
    List<Lobby> getLobbyList();
    Lobby getLobby(GameName gameName);
    Lobby joinLobby(GameName gameName, String request) throws LobbyException;

    Lobby leaveLobby(GameName gameName, String request) throws LobbyException;
}
