package it.theboys.project0002api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;

public interface BaseRepository<T, I extends Serializable> extends MongoRepository<T, I> {
    Page<T> findAll(Query query, Pageable pageable);
}
