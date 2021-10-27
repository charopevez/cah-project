package it.theboys.project0002api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class CRSController {

    //region Content Rating System Endpoints
//    @GetMapping("/crs")
//    public ResponseEntity<?> fetchCardSetsName(
//            @PathVariable String gameName) {
//        List<CardSet> setList = cardService.getSets(gameName);
//        return (setList.size() > 0) ?
//                new ResponseEntity<>(setList, HttpStatus.OK) :
//                new ResponseEntity<>(String.format("Game %s not exists or doesn't have any set", gameName), HttpStatus.NOT_FOUND);
//    }
//
//    @GetMapping("/crs/{id}")
//    public ResponseEntity<?> fetchSet(
//            @PathVariable String gameName,
//            @PathVariable String id
//    ) throws CardSetCollectionException {
//        try {
//            return new ResponseEntity<>(cardService.findSetById(id), HttpStatus.OK);
//        } catch (Exception e){
//            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
//        }
//
//    }

//    @PostMapping("/crs")
//    public ResponseEntity<?> createCrs(
//            @RequestBody String json) throws CardSetCollectionException {
//        try{
//            return new ResponseEntity<>(cardService.addSet(gameName, json), HttpStatus.OK);
//        } catch (ConstraintViolationException e) {
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
//        } catch (CardSetCollectionException | JsonProcessingException e){
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
//        }
//    }

//    @PatchMapping("/crs/{id}")
//    public ResponseEntity<?> updateSet(
//            @PathVariable String gameName,
//            @PathVariable String id,
//            @RequestBody Map<String, ?> setData) {
//        CardSet set = cardService.updateSet(id, setData);
//        return Objects.nonNull(set) ?
//                new ResponseEntity<>(set, HttpStatus.OK) :
//                new ResponseEntity<>(String.format("Couldn't find {%s}' set with id{%s}", gameName, id), HttpStatus.NOT_FOUND);
//
//    }

    //delete set from database
//    @DeleteMapping("crs/{id}")
//    public ResponseEntity<?> deleteSetById(
//            @PathVariable String gameName,
//            @PathVariable String id) throws CardSetCollectionException {
//        return cardService.deleteSet(id) ?
//                new ResponseEntity<>(String.format("Successfully deleted set \"%s\" from %s", id, gameName), HttpStatus.OK) :
//                new ResponseEntity<>(String.format("Couldn't delete set \"%s\" from %s", id, gameName), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    //endregion
}
