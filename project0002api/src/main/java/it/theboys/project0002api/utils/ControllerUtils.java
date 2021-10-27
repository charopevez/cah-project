package it.theboys.project0002api.utils;

import it.theboys.project0002api.dto.database.FilterConditionDto;
import it.theboys.project0002api.dto.database.QueryWithPageDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ControllerUtils {
    public QueryWithPageDTO generateFilterAndPaginationRepositoryQuery(
            int pageSize,
            int pageNumber,
            String orderBy,
            String filterAnd,
            String filterOr)
    {
        QueryWithPageDTO result = new QueryWithPageDTO();
        FilterBuilderUtils filterBuilder = new FilterBuilderUtils();
        // create pagination for query
        result.setPageable(filterBuilder.getPageable(pageSize, pageNumber, orderBy));
        MongoQueryBuilderUtils queryBuilder = new MongoQueryBuilderUtils();
        // create list of Conditions
        List<FilterConditionDto> andConditions=filterBuilder.createFilter(filterAnd);
        List<FilterConditionDto> orConditions=filterBuilder.createFilter(filterOr);
        // create mongodb query for db by adding filter condition
        result.setQuery(queryBuilder.addCondition(andConditions, orConditions));
        return result;
    }
}
