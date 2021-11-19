package it.theboys.project0002api.dto.http.request;

import it.theboys.project0002api.model.CardSet;
import lombok.Data;

import java.util.List;

@Data
public class AddSetRequestDto {
    private String gameName;
    private List<CardSet> setList;
}
