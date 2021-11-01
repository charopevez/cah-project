package it.theboys.project0002api.controller;

import org.springframework.http.ResponseEntity;

public class GameController {

    public ResponseEntity<?> launchGameServer(String user, String settings){
        service.launchGameServer;
    }

    public ResponseEntity<?> joinGameServer(String user, String password, String gameServerId){
        service.joinGameServer;
    }
    public ResponseEntity<?> leaveGameServer(String user, String gameServerId){
        service.leaveGameServer;
    }
    public ResponseEntity<?> terminateGameServer(String user, String gameServerId){
        service.terminateGameServer;
    }
    public ResponseEntity<?> terminateGameServer(String user, String gameServerId){
        service.messageToServer;
    }



}
