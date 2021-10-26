package it.theboys.project0002api.repository;

import it.theboys.project0002api.model.base.CahCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CahCardRepository
        extends MongoRepository<CahCard, String> {

    Optional<CahCard> findCahCardsByCardSetIn(List<String> setList);
}
