package it.theboys.project0002api.service;

import it.theboys.project0002api.enums.UserRole;
import it.theboys.project0002api.exception.database.UserCollectionException;
import it.theboys.project0002api.model.database.User;
import it.theboys.project0002api.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public User register(User user) throws UserCollectionException {
        Optional<User> inDB = userRepo.findUserByUserContact(user.getUserContact());
        if (inDB.isPresent()) throw new UserCollectionException(UserCollectionException.AlreadyExistException(user.getUserContact()));
        user.setUserRole(UserRole.ADMIN);
        user.setRegisteredAt(Instant.now().toEpochMilli());
        return userRepo.save(user);
    }

    @Override
    public String guestLogin() {
        return null;
    }

    @Override
    public String login() {
        return null;
    }

    @Override
    public String getUserList() {
        return null;
    }

    @Override
    public String getUser() {
        return null;
    }
}
