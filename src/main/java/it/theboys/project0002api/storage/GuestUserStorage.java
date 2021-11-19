package it.theboys.project0002api.storage;

import it.theboys.project0002api.model.database.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuestUserStorage {
    private static Map<String, User> userList;
    private static GuestUserStorage instance;

    private GuestUserStorage() {
        userList = new HashMap<>();
    }

    public static synchronized GuestUserStorage getInstance() {
        if (instance == null) {
            instance = new GuestUserStorage();
        }
        return instance;
    }

    public List<String> getUsernameList(){
        return new ArrayList<>(userList.keySet());
    }

    public User getUserPassword(String username) {
        return userList.get(username);
    }

    public User add(User user) {
        userList.put(user.getUserName(), user);
        return user;
    }
}
