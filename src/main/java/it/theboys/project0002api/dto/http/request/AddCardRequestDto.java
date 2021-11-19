package it.theboys.project0002api.dto.http.request;


import it.theboys.project0002api.model.database.cah.CahCard;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCardRequestDto {
    String cardSetId;
    List<CahCard> cahCards;
}
