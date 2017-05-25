package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.domain.UserAccountState;
import lv.javaguru.java2.services.exceptions.UserAccountPropertyException;
import lv.javaguru.java2.services.validators.DataInputValidator;
import lv.javaguru.java2.services.validators.UserAccountPropertyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by user on 26.03.2017.
 */
@Component
class UserAccountPropertyValidatorImpl implements UserAccountPropertyValidator {

    @Autowired private DataInputValidator inputValidator;

    private StringBuilder validationErrors = new StringBuilder();

    @Override
    public void validate(String firstName, String lastName, UserAccountState state) {
        validateFirstName(firstName);
        validateLastName(lastName);
        validateState(state);
        handleValidationErrors();
    }

    private void validateFirstName(String firstName) {
        try {
            inputValidator.validateUserAccountFirstNameInput(firstName);
        } catch (IllegalArgumentException e) {
            collectMessage(e.getMessage());
        }
    }

    private void validateLastName(String lastName) {
        try {
            inputValidator.validateUserAccountLastNameInput(lastName);
        } catch (IllegalArgumentException e) {
            collectMessage(e.getMessage());
        }
    }

    private void validateState(UserAccountState state) {
        try {
            inputValidator.validateUserAccountStateInput(state);
        } catch (IllegalArgumentException e) {
            collectMessage(e.getMessage());
        }
    }

    private void handleValidationErrors() {
        String errors = validationErrors.toString();
        if (!errors.isEmpty()) {
            throw new UserAccountPropertyException(errors);
        }
    }

    private void collectMessage(String message) {
        validationErrors.append(message).append("\n");
    }
}
