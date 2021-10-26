package it.theboys.project0002api.model.base;

import it.theboys.project0002api.enums.cah.CahSetExpansion;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;

@Data
@Document(collection = "cardSet")
public class CardSet {
    @Id
    private String id;
    @NotNull(message="Please provide game name")
    private String gameName=null;
    @NotNull(message="Please provide set name")
    private String setName=null;
    private String setDescription=null;
    private int setAgeRestrictions=17;
    @NotNull
    private CahSetExpansion setExpansion=null;
    private boolean isPrivate;
    private long addedAt;
    private long updatedAt;
}
