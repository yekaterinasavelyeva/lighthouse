package lv.javaguru.java2.services.validators;

import lv.javaguru.java2.domain.UserAccountState;

/**
 * Created by user Jekaterina Saveljeva on 26.03.2017.
 */

public interface UserAccountPropertyValidator {
    void validate(String firstName, String lastName, UserAccountState state);

}
