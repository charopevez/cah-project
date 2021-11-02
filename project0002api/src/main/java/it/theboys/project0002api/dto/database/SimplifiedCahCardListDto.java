package it.theboys.project0002api.dto.database;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.theboys.project0002api.model.database.cah.CahCard;
import it.theboys.project0002api.utils.json.serializer.SimplifiedCahCardSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonSerialize(using=SimplifiedCahCardSerializer.class)
public class SimplifiedCahCardListDto {
    List<CahCard> cardList;
}
