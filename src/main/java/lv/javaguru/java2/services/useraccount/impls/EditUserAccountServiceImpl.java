package lv.javaguru.java2.services.useraccount.impls;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.domain.UserAccountState;
import lv.javaguru.java2.services.useraccount.EditUserAccountService;
import lv.javaguru.java2.services.validators.UserAccountEditValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by user on 07.04.2017.
 */
@Component
class EditUserAccountServiceImpl implements EditUserAccountService {

    @Autowired
    @Qualifier("HibernateUserAccountDAO")
    private UserAccountDAO userAccountDAO;

    @Autowired
    private UserAccountEditValidator validator;

    @Override
    public void edit(Long accountId, String newFirstName,
                     String newLastName, UserAccountState newState) {
        validator.validate(accountId, newFirstName, newLastName, newState);
        UserAccount account = userAccountDAO.getById(accountId).get();
        account.setFirstName(newFirstName);
        account.setLastName(newLastName);
        account.setState(newState);
        userAccountDAO.update(account);
    }
}
