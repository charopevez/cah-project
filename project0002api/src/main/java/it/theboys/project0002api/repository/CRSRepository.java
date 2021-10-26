package it.theboys.project0002api.repository;

import it.theboys.project0002api.model.base.CardSet;
import it.theboys.project0002api.model.base.ContentRatingSystem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CRSRepository
        extends MongoRepository<ContentRatingSystem, String> {

      Optional<CardSet> findContentRatingSystemBySystemName(String name);

}
