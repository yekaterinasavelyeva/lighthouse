package lv.javaguru.java2.services.useraccount;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.database.jdbc.UserAccountDAOImpl;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.services.useraccount.validate.UserAccountIdValidator;
import lv.javaguru.java2.services.useraccount.validate.impls.UserAccountIdValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by user on 07.04.2017.
 */
@Component
public class FindUserAccountServiceImpl implements FindUserAccountService{

    @Autowired
    @Qualifier("HibernateUserAccountDAO")
    private UserAccountDAO userAccountDAO;

    @Autowired
    private UserAccountIdValidator userAccountValidator;

    @Override
    public UserAccount getUserAccount(Long accountId){
        userAccountValidator.validate(accountId);
        Optional<UserAccount> userAccOpt = userAccountDAO.getById(accountId);
        if (!userAccOpt.isPresent()) {
            throw new IllegalArgumentException("User account not found by id = " + accountId);
        }
        UserAccount account = userAccOpt.get();
        return account;
    }

    public void printMessage(String message){
        System.out.println(message);
    }
}
