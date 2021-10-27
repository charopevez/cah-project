package it.theboys.project0002api.dto.base;

import it.theboys.project0002api.enums.FilterOperationEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
public class FilterConditionDto {

    private String field;
    private FilterOperationEnum operator;
    private Object value;

}
