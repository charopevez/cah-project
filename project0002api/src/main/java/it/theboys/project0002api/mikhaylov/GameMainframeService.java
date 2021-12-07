package it.theboys.project0002api.mikhaylov;

import it.theboys.project0002api.enums.db.GameName;
import it.theboys.project0002api.model.cah.CahGameConfig;

public interface GameMainframeService {
    String createGame(GameName gameName, String name, CahGameConfig config);
}
