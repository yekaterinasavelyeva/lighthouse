package lv.javaguru.java2.services.exceptions;

/**
 * Created by Viktor on 2017.05.25..
 */
public class UserAccountDeleteException extends RuntimeException {

    public UserAccountDeleteException(String message) {
        super (message);
    }
}
