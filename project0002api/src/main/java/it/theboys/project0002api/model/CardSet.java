package it.theboys.project0002api.model;

import com.fasterxml.jackson.annotation.JsonView;
import it.theboys.project0002api.enums.db.GameName;
import it.theboys.project0002api.enums.cah.CahSetExpansion;
import it.theboys.project0002api.model.view.CardSetView;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;

@Data
@Document(collection = "cardSets")
@CompoundIndexes({
        @CompoundIndex(name = "game_set_idx", def = "{'gameName' : 1, 'setName': 1}", unique = true)
})
public class CardSet {
    @Id
    @JsonView(CardSetView.IdNameDescExp.class)
    private String id;
    @NotNull(message="Please provide game name")
    @Indexed
    private GameName gameName;
    @JsonView(CardSetView.IdNameDescExp.class)
    @NotNull(message="Please provide set name")
    @Indexed
    private String setName;
    @JsonView(CardSetView.IdNameDescExp.class)
    private String setDescription;
    private int setAgeRestrictions=17;
    @NotNull
    @JsonView(CardSetView.IdNameDescExp.class)
    @Indexed
    private CahSetExpansion setExpansion;
    private boolean isPrivate;
    private long addedAt;
    private long updatedAt;
}
