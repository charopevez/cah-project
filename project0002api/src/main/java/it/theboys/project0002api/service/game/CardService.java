package it.theboys.project0002api.service.game;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.theboys.project0002api.dto.base.SetListDto;
import it.theboys.project0002api.dto.http.request.CardListDTO;
import it.theboys.project0002api.exception.database.CardSetCollectionException;
import it.theboys.project0002api.model.base.CardSet;
import it.theboys.project0002api.repository.CahCardRepository;
import it.theboys.project0002api.repository.CardSetRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintDeclarationException;
import java.time.Instant;
import java.util.*;


@Service
public class CardService {
    @Autowired
    private CardSetRepository setRepo;

    public List<CardSet> getCardsBySet(String gameName) {
        switch (gameName){
            case "cah":
                return setRepo.findAll();
                default: return null;
        }
    }

    // add new set to DB
    public Map<String, List<CardSet>> addSet(String gameName, String json) throws ConstraintDeclarationException, CardSetCollectionException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        SetListDto setList = mapper.readValue(json, SetListDto.class);
        Map<String, List<CardSet>> responseBody= new HashMap<>();
        List<CardSet> added = new ArrayList<>();
        List<CardSet> existed = new ArrayList<>();
        setList.getSetList().forEach(
                (set)->{
                    Optional<CardSet> setInDB=setRepo.findCardSetByGameNameAndSetName(gameName,set.getSetName());
                    if (setInDB.isPresent()){
                        existed.add(set);
                    } else {
                        set.setGameName(gameName);
                        set.setAddedAt(Instant.now().toEpochMilli());
                        added.add(setRepo.save(set));
                    }
                }
        );
        responseBody.put("Successfully added to db",added);
        responseBody.put("Already exists in db",existed);
        return responseBody;
    }
    public List<CardSet> getSets(String gameName) {
        switch (gameName){
            case "cah":
                return setRepo.findAll();
            default: return null;
        }
    }

    public CardSet findSetById(String id) throws CardSetCollectionException {

        Optional<CardSet> set=setRepo.findById(id);
        if (set.isPresent()) {
            return set.get();

        } else {
            throw new CardSetCollectionException(CardSetCollectionException.NotFoundException(id));
            }
    }

    // TODO add user yo setAuthor
    public CardSet updateSet(String id, Map<String, ?> setData) {
        Optional<CardSet> setToUpdate=setRepo.findById(id);
        if (setToUpdate.isPresent()){
            CardSet setStoredData=setToUpdate.get();
            setData.forEach((key, value)->{
                switch (key){
                    case "gameName": setStoredData.setGameName(value.toString());break;
                    case "setName": setStoredData.setSetName(value.toString());break;
                    case "setDescription": setStoredData.setSetDescription(value.toString());break;
                    case "isCustom" : setStoredData.setPrivate((boolean) value);break;
                }
            });
            setStoredData.setUpdatedAt(Instant.now().toEpochMilli());
            return setRepo.save(setStoredData);
        }
        return null;
    }

    public boolean deleteSet(String id) throws CardSetCollectionException{
        try {
            setRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            // TODO handle exception
        }
        return false;
    }


}
