package it.theboys.project0002api.service.cardgame;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameMainframeServiceImpl implements GameMainframeService {


    @Override
    public String launchGameServer(String user, String settings) {
        return "null";
    }

    @Override
    public String joinGameServer(String user, String password, String gameServerId) {
        return "null";
    }

    @Override
    public String leaveGameServer(String user, String gameServerId) {
        return "null";
    }

    @Override
    public String terminateGameServer(String user, String gameServerId) {
        return "null";
    }

    @Override
    public String messageToServer(String user, String gameServerId) {
        return "null";
    }
}
