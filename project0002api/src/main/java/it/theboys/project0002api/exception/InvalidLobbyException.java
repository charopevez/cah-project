package it.theboys.project0002api.exception;

public class InvalidLobbyException extends Exception {
    private String message;

    public InvalidLobbyException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
