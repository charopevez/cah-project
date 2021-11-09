package it.theboys.project0002api.service;

import it.theboys.project0002api.exception.database.UserCollectionException;
import it.theboys.project0002api.model.database.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {


    User register(User user) throws UserCollectionException;
    String guestLogin();
    String login();
    String getUserList();
    String getUser();
}
