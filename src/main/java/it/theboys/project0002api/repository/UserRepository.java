package it.theboys.project0002api.repository;
import it.theboys.project0002api.model.database.User;
import org.springframework.data.mongodb.repository.Aggregation;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends BaseRepository<User, String> {

   // Optional<User> findUserByUserContact(String email);
    Optional<User> findUserByUserName(String username);
    Optional<User> findUserByVerificationCode(String code);
    @Aggregation(pipeline = { "{ '$group': { '_id' : '$userName' } }" })
    List<String> findDistinctUserName();

}
