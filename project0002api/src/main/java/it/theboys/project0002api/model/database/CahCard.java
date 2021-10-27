package it.theboys.project0002api.model.database;

import it.theboys.project0002api.enums.cah.CahCardAction;
import it.theboys.project0002api.enums.cah.CahCardType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document
@CompoundIndexes({
        @CompoundIndex(name = "cardSet_cardText", def = "{'cardsSet' : 1, 'cardText': 1}", unique = true)
})
public class CahCard {
    @Id
    private String cardId ;
    private String cardText;
    private String cardSet;
    private String cardImage;
    private CahCardType cardType;
    private Map<CahCardAction, Integer> cardAction;

    public CahCard(String cardText,
                   String cardSet,
                   String cardImage,
                   CahCardType cardType,
                   Map<CahCardAction, Integer> cardAction) {
        this.cardText = cardText;
        this.cardSet = cardSet;
        this.cardImage = cardImage;
        this.cardType = cardType;
        this.cardAction = cardAction;
    }
}
