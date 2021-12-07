package it.theboys.project0002api.mikhaylov;

import it.theboys.project0002api.enums.db.GameName;
import it.theboys.project0002api.model.cah.CahGameConfig;
import org.springframework.stereotype.Service;

@Service
public class GameMainframeServiceImpl implements GameMainframeService {
    @Override
    public String createGame(GameName gameName, String name, CahGameConfig config) {
        return null;
    }
}
