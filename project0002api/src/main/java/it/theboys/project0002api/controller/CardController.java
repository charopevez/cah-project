package it.theboys.project0002api.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import it.theboys.project0002api.dto.http.request.CardListDTO;
import it.theboys.project0002api.exception.database.CardSetCollectionException;
import it.theboys.project0002api.model.base.CahCard;
import it.theboys.project0002api.model.base.CardSet;
import it.theboys.project0002api.service.game.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class CardController {

    @Autowired
    private CardService cardService;


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
    @PatchMapping("{gameName}/card")
    public String updateCard(
            @RequestBody CardListDTO cardList,
            @PathVariable String gameName) {
        return cardList.toString();
    }

    //TODO add update
    @DeleteMapping("/{gameName}/card")
    public String deleteCard(
            @PathVariable String gameName,
            @RequestParam List<String> ids) {
        return ids.toString();
    }
    //endregion

    //region CardSet Endpoints
    @GetMapping("/{gameName}/set")
    public ResponseEntity<?> fetchCardSetsName(
            @PathVariable String gameName) {
        List<CardSet> setList = cardService.getSets(gameName);
        return (setList.size() > 0) ?
                new ResponseEntity<>(setList, HttpStatus.OK) :
                new ResponseEntity<>(String.format("Game %s not exists or doesn't have any set", gameName), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{gameName}/set/{id}")
    public ResponseEntity<?> fetchSet(
            @PathVariable String gameName,
            @PathVariable String id
    ) throws CardSetCollectionException {
        try {
            return new ResponseEntity<>(cardService.findSetById(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/{gameName}/set")
    public ResponseEntity<?> addSet(
            @PathVariable String gameName,
            @RequestBody String json) throws CardSetCollectionException {
        try{
            return new ResponseEntity<>(cardService.addSet(gameName, json), HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (CardSetCollectionException | JsonProcessingException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
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
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    //delete set from database
    @DeleteMapping("{gameName}/set/{id}")
    public ResponseEntity<?> deleteSetById(
            @PathVariable String gameName,
            @PathVariable String id) throws CardSetCollectionException {
        try {
            return new ResponseEntity<>(cardService.deleteSet(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    //endregion

}
