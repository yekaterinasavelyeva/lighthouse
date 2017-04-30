package lv.javaguru.java2.services.useraccount;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.database.jdbc.UserAccountDAOImpl;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.domain.UserAccountState;
import lv.javaguru.java2.services.useraccount.validate.UserAccountIdValidator;
import lv.javaguru.java2.services.useraccount.validate.impls.UserAccountIdValidatorImpl;
import lv.javaguru.java2.services.useraccount.validate.UserAccountValidator;
import lv.javaguru.java2.services.useraccount.validate.impls.UserAccountValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by user on 07.04.2017.
 */
@Component
public class EditUserAccountServiceImpl implements EditUserAccountService {

    @Autowired
    private UserAccountDAO userAccountDAO;
    @Autowired
    private UserAccountValidator userAccountValidator;
    @Autowired
    private UserAccountIdValidator userAccountIdValidator;

    @Override
    public void edit(Long accountId,
                     String newFirstName,
                     String newLastName, UserAccountState newState) {
        userAccountIdValidator.validate(accountId);
        Optional<UserAccount> userAccOpt = userAccountDAO.getById(accountId);
        if (!userAccOpt.isPresent()) {
            throw new IllegalArgumentException("User account not found by id = " + accountId);
        }

        userAccountValidator.validate(newFirstName, newLastName, newState);

        UserAccount account = userAccOpt.get();
        account.setFirstName(newFirstName);
        account.setLastName(newLastName);
        account.setState(newState);
        userAccountDAO.update(account);
    }
}
