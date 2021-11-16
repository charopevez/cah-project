package it.theboys.project0002api.model.cah;

import java.util.List;

import it.theboys.project0002api.model.database.cah.CahCard;
import lombok.Data;
@Data
public class CahPlayer {
    
    private String playerName;

    private List<CahCard> playerHand;

    private int playerSeat;

    private List<CahCard> playerAnswer;

    private int playerScore;

    private  String playerAvatar;

    public void draw(CahCard card) {
        playerHand.add(card);
    }

    public void clearAnswer(){
        playerAnswer=null;
    }
}
