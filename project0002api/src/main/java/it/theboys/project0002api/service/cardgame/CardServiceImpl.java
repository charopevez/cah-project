package it.theboys.project0002api.service.cardgame;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.theboys.project0002api.dto.http.request.SetListDto;
import it.theboys.project0002api.exception.database.CardSetCollectionException;
import it.theboys.project0002api.exception.database.ImmutableFieldException;
import it.theboys.project0002api.model.database.CardSet;
import it.theboys.project0002api.repository.CardSetRepository;
import it.theboys.project0002api.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintDeclarationException;
import java.time.Instant;
import java.util.*;


@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardSetRepository setRepo;
    private final String[] immutableSetFields=new String[]{"id", "gameName", "addedAt", "updatedAt"};

    /**
            * {@inheritDoc}
     */
    public List<CardSet> getCardsBySet(String gameName) {
        switch (gameName) {
            case "cah":
                return setRepo.findAll();
            default:
                return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<CardSet> getSets(Query query, Pageable pageable) {
        return setRepo.findAll(query, pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardSet getSetById(String id) throws CardSetCollectionException {

        Optional<CardSet> set = setRepo.findById(id);
        if (set.isPresent()) {
            return set.get();
        } else {
            throw new CardSetCollectionException(CardSetCollectionException.NotFoundException(id));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    // TODO add user yo setAuthor
    public CardSet updateSet(String id, CardSet newSetData, String gameName) throws CardSetCollectionException, ImmutableFieldException {
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
        CardSet setToSave=new ObjectUtils<CardSet>().updateObjectFromObject(updatingSet,newSetData, immutableSetFields);
        setToSave.setUpdatedAt(Instant.now().toEpochMilli());
        return setRepo.save(setToSave);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteSet(String id) throws CardSetCollectionException {
        Optional<CardSet> setById = setRepo.findById(id);
        if (setById.isPresent()) {
            setRepo.deleteById(id);
        } else {
            throw new CardSetCollectionException(CardSetCollectionException.NotFoundException(id));
        }
    }


}
