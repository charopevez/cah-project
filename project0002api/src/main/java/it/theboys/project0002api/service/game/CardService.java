package it.theboys.project0002api.service.game;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.theboys.project0002api.dto.base.SetListDto;
import it.theboys.project0002api.exception.database.CardSetCollectionException;
import it.theboys.project0002api.model.base.CardSet;
import it.theboys.project0002api.repository.CardSetRepository;
import it.theboys.project0002api.utils.ObjectUtils;
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
        switch (gameName) {
            case "cah":
                return setRepo.findAll();
            default:
                return null;
        }
    }

    // add new set to DB
    public Map<String, List<CardSet>> addSet(String gameName, String json) throws ConstraintDeclarationException, CardSetCollectionException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        SetListDto setList = mapper.readValue(json, SetListDto.class);
        Map<String, List<CardSet>> responseBody = new HashMap<>();
        List<CardSet> added = new ArrayList<>();
        List<CardSet> existed = new ArrayList<>();
        setList.getSetList().forEach(
                (set) -> {
                    Optional<CardSet> setInDB = setRepo.findCardSetByGameNameAndSetName(gameName, set.getSetName());
                    if (setInDB.isPresent()) {
                        existed.add(set);
                    } else {
                        set.setGameName(gameName);
                        set.setAddedAt(Instant.now().toEpochMilli());
                        added.add(setRepo.save(set));
                    }
                }
        );
        responseBody.put("Success", added);
        responseBody.put("Failure", existed);
        return responseBody;
    }

    public List<CardSet> getSets(String gameName) {
        switch (gameName) {
            case "cah":
                return setRepo.findAll();
            default:
                return null;
        }
    }

    public CardSet findSetById(String id) throws CardSetCollectionException {

        Optional<CardSet> set = setRepo.findById(id);
        if (set.isPresent()) {
            return set.get();
        } else {
            throw new CardSetCollectionException(CardSetCollectionException.NotFoundException(id));
        }
    }

    // TODO add user yo setAuthor
    public CardSet updateSet(String id, CardSet newSetData, String gameName) throws CardSetCollectionException {
        Optional<CardSet> setById = setRepo.findById(id);
        // check if set with @param id is exists
        if (setById.isEmpty()) {
            //if not try add set to db
            newSetData.setGameName(gameName);
            newSetData.setAddedAt(Instant.now().toEpochMilli());
            return setRepo.save(newSetData);
        }
        //check if set with new name exist in this @param gameName
        Optional<CardSet> setByName = setRepo.findCardSetByGameNameAndSetName(gameName, newSetData.getSetName());
        if (setByName.isPresent() && !setByName.get().getId().equals(id)) {
            throw new CardSetCollectionException(CardSetCollectionException.AlreadyExistException(gameName, newSetData.getSetName()));
        }
        CardSet updatingSet = setById.get();
        List<String> setterFilter= new ArrayList<String>();
        return setRepo.save(new ObjectUtils<CardSet>().updateObjectFromObject(updatingSet,newSetData, setterFilter));
    }

    public boolean deleteSet(String id) throws CardSetCollectionException {
        try {
            setRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            // TODO handle exception
        }
        return false;
    }


}
