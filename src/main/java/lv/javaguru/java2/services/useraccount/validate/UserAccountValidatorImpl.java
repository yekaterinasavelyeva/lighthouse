package lv.javaguru.java2.services.useraccount.validate;

import lv.javaguru.java2.domain.UserAccountState;

/**
 * Created by user on 26.03.2017.
 */
public class UserAccountValidatorImpl implements UserAccountValidator {

    private StringBuilder validationErrors = new StringBuilder();

    @Override
    public void validate(String firstName, String lastName, UserAccountState state) {
        validateFirstName(firstName);
        validateLastName(lastName);
        validateState(state);
        handleValidationErrors();
    }

    private void validateFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            validationErrors.append("First Name cannot be empty!\n");
        }
    }

    private void validateLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            validationErrors.append("Last Name cannot be empty!\n");
        }
    }

    private void validateState(UserAccountState state) {
        if (state == null) {
            validationErrors.append("Account type cannot be empty!\n");
        }
    }

    private void handleValidationErrors() {
        String errors = validationErrors.toString();
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(errors);
        }
    }
}
