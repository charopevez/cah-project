package it.theboys.project0002api.repository;

import it.theboys.project0002api.model.database.CardSet;
import org.springframework.stereotype.Repository;

@Repository
public interface CardSetRepository
        extends BaseRepository<CardSet, String> {
}
