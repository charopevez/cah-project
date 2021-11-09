package it.theboys.project0002api.repository;

import it.theboys.project0002api.model.database.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, String> {

    Optional<User> findUserByUserContact(String email);

}
