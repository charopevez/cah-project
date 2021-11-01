package it.theboys.project0002api.controller;

import it.theboys.project0002api.service.cardgame.GameMainframeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class GameController {
    private final GameMainframeService gameService;

    public ResponseEntity<?> launchGameServer(String user, String settings) {
        return new ResponseEntity<>(gameService.launchGameServer(user, settings), HttpStatus.OK);
    }

    public ResponseEntity<?> joinGameServer(String user, String password, String gameServerId) {
        return new ResponseEntity<>(gameService.joinGameServer(user, password, gameServerId), HttpStatus.OK);
    }

    public ResponseEntity<?> leaveGameServer(String user, String gameServerId) {
        return new ResponseEntity<>(gameService.leaveGameServer(user, gameServerId), HttpStatus.OK);
    }

    public ResponseEntity<?> terminateGameServer(String user, String gameServerId) {
        return new ResponseEntity<>(gameService.terminateGameServer(user, gameServerId), HttpStatus.OK);
    }

    public ResponseEntity<?> messageToServer(String user, String gameServerId) {
        return new ResponseEntity<>(gameService.messageToServer(user, gameServerId), HttpStatus.OK);
    }


}
