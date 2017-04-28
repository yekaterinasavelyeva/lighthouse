package lv.javaguru.java2.services.useraccount.validate.impls;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.services.useraccount.validate.UserAccountIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by user on 15.04.2017.
 */
@Component
public class UserAccountIdValidatorImpl implements UserAccountIdValidator {

    @Autowired
    private UserAccountDAO userAccountDAO;

    public void validate(Long userAccountId){
        validateNotNullAndNotNegative(userAccountId);
        validateExistence(userAccountId);
    }

    private  void validateNotNullAndNotNegative(Long userAccountId){
        if (userAccountId == null || userAccountId<0) {
            throw new IllegalArgumentException("Account ID must not be empty or less than 0");
        }
    }

    private void validateExistence(Long userAccountId) {
        Optional<UserAccount> userAccountFromDB = userAccountDAO.getById(userAccountId);
        if (!userAccountFromDB.isPresent()) {
            throw new IllegalArgumentException("User account not found id = " + userAccountId);
        }
    }
}
