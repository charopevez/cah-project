package it.theboys.project0002api.service;

import com.mongodb.MongoWriteException;
import it.theboys.project0002api.exception.database.UserCollectionException;
import it.theboys.project0002api.model.database.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {


    User register(User user) throws UserCollectionException, MongoWriteException;


    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    User getUserById(String id) throws UserCollectionException;

    User getUserByUsername(String username) throws UserCollectionException;

    User verifyUser(String code) throws UserCollectionException;

    List<String> getUsernameList();

    void deleteUser(String id) throws UserCollectionException;

    User modifyUser(String id, String request) throws UserCollectionException;
}
