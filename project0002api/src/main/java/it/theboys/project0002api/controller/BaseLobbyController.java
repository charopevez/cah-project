package it.theboys.project0002api.controller;

import it.theboys.project0002api.dto.http.request.LobbyJoinRequestDTO;
import it.theboys.project0002api.exception.InvalidLobbyException;
import it.theboys.project0002api.exception.InvalidParamException;
import it.theboys.project0002api.model.base.Lobby;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/lobby")
public class BaseLobbyController {
// toDo create GameServer
//  private final GameService gameService;

//    // handle http request to join to Lobby
//    @PostMapping("/join")
//    public ResponseEntity<Lobby> join(@RequestBody LobbyJoinRequestDTO request)
//            throws InvalidParamException, InvalidLobbyException {
//        // toDO log "user joins to Lobby"
//        return ResponseEntity.ok(lobbyService.joinToLobby(request.getUser(), request.getLobbyId()));
//    }
}
