package it.theboys.project0002api.service.cardgame;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.theboys.project0002api.dto.base.FilterConditionDto;
import it.theboys.project0002api.exception.database.CardSetCollectionException;
import it.theboys.project0002api.exception.database.ImmutableFieldException;
import it.theboys.project0002api.model.database.CardSet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Map;

public interface CardService {

    /**
     * Get all custom paginate data for entity CardSet
     *
     * @param query    custom query
     * @param pageable pageable param
     * @return Page of entity CardSet
     */
    Page<CardSet> getSets(Query query, Pageable pageable);
    /**
     * Get all custom paginate data for entity CardSet
     *
     * @param gameName    game Name
     * @param json request body json{}
     * @return Map<String, List<CardSet>>
     */
    Map<String, List<CardSet>> addSet(String gameName, String json) throws CardSetCollectionException, JsonProcessingException;

    /**
     * Return Set Info and all cards in this set
     *
     * @param id   set ID
     * @return CardSet
     */
    CardSet getSetById(String id) throws CardSetCollectionException;

    /**
     * Update Set with ID by new data
     *
     * @param id   set ID
     * @param gameName game name
     * @param setData new data
     * @return CardSet
     */
    CardSet updateSet(String id, CardSet setData, String gameName) throws CardSetCollectionException, ImmutableFieldException;

    /**
     * Delete Set by ID
     *
     * @param id   set ID
     *
     */
    void deleteSet(String id) throws CardSetCollectionException;
}
