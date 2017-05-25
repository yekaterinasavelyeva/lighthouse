package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.services.exceptions.UserAccountDeleteException;
import lv.javaguru.java2.services.validators.DataExistValidator;
import lv.javaguru.java2.services.validators.DataInputValidator;
import lv.javaguru.java2.services.validators.LibraryRuleValidator;
import lv.javaguru.java2.services.validators.UserAccountDeleteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Viktor on 2017.05.25..
 */
@Component
public class UserAccountDeleteValidatorImpl implements UserAccountDeleteValidator {
    @Autowired
    private DataExistValidator dataExistValidator;
    @Autowired
    private DataInputValidator dataInputValidator;
    @Autowired
    private LibraryRuleValidator ruleValidator;

    @Override
    public void validate(Long userAccountId) {
        try {
            dataInputValidator.validateUserAccountIdInput(userAccountId);
            dataExistValidator.validateUserAccountIdExists(userAccountId);
            ruleValidator.validateReservationStatusWhenDeletingUserAccount(userAccountId);
        } catch (IllegalArgumentException e) {
            throw new UserAccountDeleteException(e.getMessage());
        }
    }
}
