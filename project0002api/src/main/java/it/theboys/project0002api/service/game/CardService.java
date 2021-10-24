package it.theboys.project0002api.service.game;

import it.theboys.project0002api.exception.database.CardSetCollectionException;
import it.theboys.project0002api.model.base.CardSet;
import it.theboys.project0002api.repository.CahCardRepository;
import it.theboys.project0002api.repository.CardSetRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintDeclarationException;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;


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
    public CardSet addSet(String gameName, CardSet set) throws ConstraintDeclarationException,CardSetCollectionException {

//        Optional<CardSet> setInDB=setRepo.findCardSetByGameNameAndSetName(gameName,set.getSetName());
//        if (setInDB.isPresent()){
//            throw new CardSetCollectionException(CardSetCollectionException.AlreadyExistException(gameName,set.getSetName()));
//        }
            set.setGameName(gameName);
            set.setAddedAt(Instant.now().toEpochMilli());
            return setRepo.save(set);
    }

    public Optional<CardSet> findSetById(String id) {
        return setRepo.findById(id);
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

    public boolean deleteSet(String id) {
        try {
            setRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            // TODO handle exception
        }
        return false;
    }

    public List<CardSet> getSets(String gameName) {
        switch (gameName){
            case "cah":
                return setRepo.findAll();
            default: return null;
        }
    }
}
