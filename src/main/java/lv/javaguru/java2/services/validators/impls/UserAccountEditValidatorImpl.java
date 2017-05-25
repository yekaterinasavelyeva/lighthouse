package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.domain.UserAccountState;
import lv.javaguru.java2.services.exceptions.UserAccountEditException;
import lv.javaguru.java2.services.exceptions.UserAccountPropertyException;
import lv.javaguru.java2.services.validators.DataExistValidator;
import lv.javaguru.java2.services.validators.DataInputValidator;
import lv.javaguru.java2.services.validators.UserAccountEditValidator;
import lv.javaguru.java2.services.validators.UserAccountPropertyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Viktor on 2017.05.25..
 */
@Component
public class UserAccountEditValidatorImpl implements UserAccountEditValidator {
    @Autowired private DataInputValidator inputValidator;
    @Autowired private DataExistValidator existValidator;
    @Autowired private UserAccountPropertyValidator propertyValidator;

    private StringBuilder validationErrors = new StringBuilder();

    @Override
    public void validate(Long userAccountId, String firstName, String lastName, UserAccountState state) {
        validateUserAccountId(userAccountId);
        validateUserAccountProperties(firstName, lastName, state);
        handleValidationErrors();
    }

    private void validateUserAccountId(Long userAccountId) {
        try {
            inputValidator.validateUserAccountIdInput(userAccountId);
            existValidator.validateUserAccountIdExists(userAccountId);
        } catch (IllegalArgumentException e) {
            collectMessage(e.getMessage());
        }
    }

    private void validateUserAccountProperties(String firstName,
                                               String lastName,
                                               UserAccountState state) {
        try {
            propertyValidator.validate(firstName, lastName, state);
        } catch (UserAccountPropertyException e) {
            collectMessage(e.getMessage());
        }
    }

    private void handleValidationErrors() {
        String errors = validationErrors.toString();
        if(!errors.isEmpty()) {
            throw new UserAccountEditException(errors);
        }
    }

    private void collectMessage(String message) {
        validationErrors.append(message).append("\n");
    }

}
