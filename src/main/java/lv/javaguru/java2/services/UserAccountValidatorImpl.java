package lv.javaguru.java2.services;

import lv.javaguru.java2.domain.UserAccountState;

/**
 * Created by user on 26.03.2017.
 */
public class UserAccountValidatorImpl implements UserAccountValidator {

    @Override
    public void validate(String firstName, String lastName, UserAccountState state) {
        validateFirstName(firstName);
        validateLastName(lastName);
        validateState(state);
    }

    private void validateFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First Name must be not empty!");
        }
    }

    private void validateLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last Name must be not empty!");
        }
    }

    private void validateState(UserAccountState state) {
        if (state == null) {
            throw new IllegalArgumentException("Account type must be not empty!");
        }
    }
}
