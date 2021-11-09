package it.theboys.project0002api.exception.database;

public class UserCollectionException extends Exception {

    public UserCollectionException(String message){
        super(message);
    }

    public static String NotFoundException(String id){
        return String.format("Set with %s id is not found", id);
    }

    public static String AlreadyExistException(String email){
        return String.format("User with %s email is already exists in database",email);
    }
}
