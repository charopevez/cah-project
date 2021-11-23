package it.theboys.project0002api.service;

import it.theboys.project0002api.enums.db.GameName;
import it.theboys.project0002api.exception.LobbyException;
import it.theboys.project0002api.model.Lobby;
import it.theboys.project0002api.storage.LobbyStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LobbyServiceImpl implements LobbyService {

    @Override
    public List<Lobby> getLobbyList() {
        return LobbyStorage.getInstance().getLobbyList();
    }

    @Override
    public Lobby getLobby(GameName gameName) {
        return LobbyStorage.getInstance().getLobbyMap().get(gameName);
    }

    @Override
    public Lobby joinLobby(GameName gameName, String userId) throws LobbyException {
        if (!LobbyStorage.getInstance().getLobbyMap().containsKey(gameName)) {
            throw new LobbyException(LobbyException.NotFoundException(gameName));
        }
        Lobby lobby = LobbyStorage.getInstance().getLobbyMap().get(gameName);
        //check if lobby contains player from request
        if (lobby.getPlayerList().contains(userId)) {
            return lobby;
        } else {
            lobby.addPlayer(userId);
        }
        LobbyStorage.getInstance().setLobby(lobby);
        return lobby;
    }

    @Override
    public Lobby leaveLobby(GameName gameName, String userId) throws LobbyException {
        if (!LobbyStorage.getInstance().getLobbyMap().containsKey(gameName)) {
            throw new LobbyException(LobbyException.NotFoundException(gameName));
        }
        Lobby lobby = LobbyStorage.getInstance().getLobbyMap().get(gameName);
        //check if lobby contains player from request
        if (!lobby.getPlayerList().contains(userId)) {
            throw new LobbyException(LobbyException.UserNotFoundException(gameName, userId));
        } else {
            lobby.removePlayer(userId);
        }
        LobbyStorage.getInstance().setLobby(lobby);
        return lobby;
    }


}
