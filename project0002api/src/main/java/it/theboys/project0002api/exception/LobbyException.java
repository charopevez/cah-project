package it.theboys.project0002api.exception;

import it.theboys.project0002api.enums.GameName;

public class LobbyException extends Throwable {
    public LobbyException(String message){
        super(message);
    }

    public static String NotFoundException(GameName gameName){
        return String.format("%s lobby don't exists", gameName);
    }

    public static String AlreadyExistException(String gameName,String setName){
        return String.format("Set with %s name is already exists in &s", setName, gameName);
    }

    public static String UserNotFoundException(GameName gameName, String request) {
        return String.format("User with %s username not found in &s", request, gameName);
    }
}
