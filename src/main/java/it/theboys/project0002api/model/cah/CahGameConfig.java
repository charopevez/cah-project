package it.theboys.project0002api.model.cah;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.theboys.project0002api.model.CardSet;
import it.theboys.project0002api.model.view.CardSetView;
import it.theboys.project0002api.utils.json.serializer.CahGameConfigSerializer;
import lombok.Data;

import java.util.List;

@Data
@JsonSerialize(using = CahGameConfigSerializer.class)
public class CahGameConfig {
    int[] playerLimit = {3, 10};
    String serverName;
    String serverPassword;
    int timeLimit = 60;
    int scoreLimit = 8;
    List<CardSet> setList;
}
