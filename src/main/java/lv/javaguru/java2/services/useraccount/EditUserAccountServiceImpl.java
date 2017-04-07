package lv.javaguru.java2.services.useraccount;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.database.jdbc.UserAccountDAOImpl;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.domain.UserAccountState;
import lv.javaguru.java2.services.validate.UserAccountValidator;
import lv.javaguru.java2.services.validate.UserAccountValidatorImpl;

import java.util.Optional;

/**
 * Created by user on 07.04.2017.
 */
public class EditUserAccountServiceImpl implements EditUserAccountService {

    private UserAccountDAO userAccountDAO = new UserAccountDAOImpl();
    private UserAccountValidator userAccountValidator = new UserAccountValidatorImpl();

    @Override
    public void edit(Long accountId,
                     String newFirstName,
                     String newLastName, UserAccountState newState) {
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
