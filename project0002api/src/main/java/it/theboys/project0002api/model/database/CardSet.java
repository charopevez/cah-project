package it.theboys.project0002api.model.database;

import it.theboys.project0002api.enums.cah.CahSetExpansion;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;

@Data
@Document(collection = "cardSets")
public class CardSet {
    @Id
    private String id;
    @NotNull(message="Please provide game name")
    private String gameName;
    @NotNull(message="Please provide set name")
    private String setName;
    private String setDescription;
    private int setAgeRestrictions=17;
    @NotNull
    private CahSetExpansion setExpansion;
    private boolean isPrivate;
    private long addedAt;
    private long updatedAt;
}
