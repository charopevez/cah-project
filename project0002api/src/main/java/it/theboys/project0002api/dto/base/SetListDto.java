package it.theboys.project0002api.dto.base;

import it.theboys.project0002api.model.base.CardSet;
import lombok.Data;

import java.util.List;

@Data
public class SetListDto {
    private List<CardSet> setList;
}
