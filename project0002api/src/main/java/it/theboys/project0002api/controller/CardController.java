package it.theboys.project0002api.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import it.theboys.project0002api.dto.database.QueryWithPageDTO;
import it.theboys.project0002api.dto.http.response.PageResponseDTO;
import it.theboys.project0002api.exception.database.BadRequestException;
import it.theboys.project0002api.exception.database.CardSetCollectionException;
import it.theboys.project0002api.exception.database.ImmutableFieldException;
import it.theboys.project0002api.model.database.CahCard;
import it.theboys.project0002api.model.database.CardSet;
import it.theboys.project0002api.service.cardgame.CardService;
import it.theboys.project0002api.utils.ControllerUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class CardController {

    private final CardService cardService;


    //region Cards endpoints
    @GetMapping("/{gameName}/card")
    public String fetchCards(
            @PathVariable String gameName,
            @RequestParam Optional<List<String>> ids) {
        // if param exist return card list
        if (ids.isPresent()) {
            return ids.toString();
        }
        // if not return all
        return "all";
    }


    @PostMapping("/{gameName}/card")
    public String addCard(
            @RequestBody CahCard card,
            @PathVariable String gameName) {
        return card.toString();
        //return cardService.addCard(gameName, cardList);
    }

    //TODO add update

    //TODO add update
    @DeleteMapping("/{gameName}/card")
    public String deleteCard(
            @PathVariable String gameName,
            @RequestParam List<String> ids) {
        return ids.toString();
    }
    //endregion

    //region CardSet Endpoints

    /**
     * search with pagination
     *
     * @param pageNumber page number
     * @param pageSize   page item count
     * @param filterOr   string filter or conditions
     * @param filterAnd  string filter and conditions
     * @param orderBy    sting order items by
     * @return PageResponse<CardSet>
     */
    @GetMapping("/{gameName}/set/page")
    public ResponseEntity<?> fetchSetByPages(
            @PathVariable String gameName,
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
            @RequestParam(value = "filterOr", required = false) String filterOr,
            @RequestParam(value = "filterAnd", required = false) String filterAnd,
            @RequestParam(value = "orderBy", required = false) String orderBy) {
        // initialize variable to be returned
        try {
            PageResponseDTO<CardSet> responseBody = new PageResponseDTO<>();
            QueryWithPageDTO serviceRequest = new ControllerUtils().generateFilterAndPaginationRepositoryQuery(
                    pageSize, pageNumber, orderBy, filterAnd, filterOr);
            Page<CardSet> page = cardService.getSetPages(serviceRequest);
            responseBody.setPageStats(page, page.getContent());
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * search without pagination
     *
     * @param filterOr   string filter or conditions
     * @param filterAnd  string filter and conditions
     * @return PageResponse<CardSet>
     */
    @GetMapping("/{gameName}/set")
    public ResponseEntity<?> fetchSet(
            @PathVariable String gameName,
            @RequestParam(value = "filterOr", required = false) String filterOr,
            @RequestParam(value = "filterAnd", required = false) String filterAnd){
        // initialize variable to be returned
        try {

            Query serviceRequest = new ControllerUtils().generateFilterRepositoryQuery(
                    filterAnd, filterOr);
            List<CardSet> responseBody = cardService.getSets(serviceRequest);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{gameName}/set/{id}")
    public ResponseEntity<?> fetchSet(
            @PathVariable String gameName,
            @PathVariable String id
    ) throws CardSetCollectionException {
        try {
            return new ResponseEntity<>(cardService.getSetById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/{gameName}/set")
    public ResponseEntity<?> addSet(
            @PathVariable String gameName,
            @RequestBody String json) throws CardSetCollectionException {
        try {
            return new ResponseEntity<>(cardService.addSet(gameName, json), HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (CardSetCollectionException | JsonProcessingException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PatchMapping("/{gameName}/set/{id}")
    public ResponseEntity<?> updateSet(
            @PathVariable String gameName,
            @PathVariable String id,
            @RequestBody CardSet setData) {
        try {
            return new ResponseEntity<>(cardService.updateSet(id, setData, gameName), HttpStatus.OK);
        } catch (CardSetCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ConstraintViolationException | ImmutableFieldException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    //delete set from database
    @DeleteMapping("{gameName}/set/{id}")
    public ResponseEntity<?> deleteSetById(
            @PathVariable String gameName,
            @PathVariable String id) throws CardSetCollectionException {
        try {
            cardService.deleteSet(id);
            return new ResponseEntity<>("Successfully deleted set with id " + id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //endregion

}
