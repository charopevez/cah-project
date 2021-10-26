package it.theboys.project0002api.repository;

import it.theboys.project0002api.model.base.CardSet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardSetRepository
        extends MongoRepository<CardSet, String> {

      Optional<CardSet> findCardSetByGameNameAndSetName(String gameName, String setName);
//    Optional<CardSet> findBySetAndSetAgeRestrictionsIsGreaterThanEqual(int age);

}
