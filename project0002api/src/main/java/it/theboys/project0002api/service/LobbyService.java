package it.theboys.project0002api.service;

import it.theboys.project0002api.exception.InvalidParamException;
import it.theboys.project0002api.model.base.Lobby;
import it.theboys.project0002api.model.base.User;
import it.theboys.project0002api.storage.LobbyStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class LobbyService {

    public Lobby launchLobby() {
        Lobby lobby=new Lobby();
        lobby.setLobbyId(UUID.randomUUID().toString());
        LobbyStorage.getInstance().setLobby(lobby);
        return lobby;
    }

    // Join to @param lobbyId
    public Lobby joinToLobby(User user, String lobbyId)
            throws InvalidParamException {
        // check if lobby with @param lobbyId is exists
        if (!LobbyStorage.getInstance().getLobbyList().containsKey(lobbyId)) {
            throw new InvalidParamException("Lobby with provided id doesn't exist");
        }
        Lobby lobby = LobbyStorage.getInstance().getLobbyList().get(lobbyId);

        lobby.setUser(user);
        LobbyStorage.getInstance().setLobby(lobby);
        return lobby;
    }
}
