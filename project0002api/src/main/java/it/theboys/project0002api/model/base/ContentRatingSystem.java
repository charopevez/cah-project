package it.theboys.project0002api.model.base;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "cardSet")
public class ContentRatingSystem {
    @Id
    private String id;
    String systemCountry;
    String systemName;
    List<AgeBracket> systemAgeBracket;

    private class AgeBracket {
        int bracketMax;
        int bracketMin;
        String bracketImage;
        BracketRule bracketRule;
    }
}
