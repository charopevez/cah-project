package it.theboys.project0002api.exception.security;

public class AppSecurityException extends Exception {

    public AppSecurityException(String message){
        super(message);
    }

    public static String TokenNotFoundException(){
        return "Refresh token is missing";
    }
}
