package it.theboys.project0002api.dto.http.response;

import com.fasterxml.jackson.annotation.JsonView;
import it.theboys.project0002api.model.CardSet;
import it.theboys.project0002api.model.database.cah.CahCard;
import it.theboys.project0002api.model.view.CardSetView;
import lombok.Data;

import java.util.List;

@Data
@JsonView(CardSetView.IdNameDescExp.class)
public class SetWithCardsResponseDto {
    CardSet setInfo;
    List<CahCard> cardList;
}
