package lv.javaguru.java2.services.useraccount;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.database.jdbc.UserAccountDAOImpl;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.services.validate.UserAccountValidator;
import lv.javaguru.java2.services.validate.UserAccountValidatorImpl;

import java.util.Optional;

/**
 * Created by user on 07.04.2017.
 */
public class FindUserAccountServiceImpl implements FindUserAccountService{

    private UserAccountDAO userAccountDAO = new UserAccountDAOImpl();
    private UserAccountValidator userAccountValidator = new UserAccountValidatorImpl();

    @Override
    public UserAccount getUserAccount(Long accountId){
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