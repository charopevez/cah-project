package it.theboys.project0002api.exception.database;

public class UserCollectionException extends Exception {

    public UserCollectionException(String message){
        super(message);
    }

    public static String NotFoundException(String id){
        return String.format("User with %s id is not found", id);
    }

    public static String AlreadyExistException(String email){
        return String.format("User with %s email is already exists in database",email);
    }

    public static String VerificationCodeException(String code){
        return String.format("Code %s is not found or already used to verify user",code);
    }

    public static String UsernameConstraintException(String userName) {
        return String.format("User with username \"%s\" is already exists", userName);
    }
}
