package lv.javaguru.java2.services.useraccount;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.database.jdbc.UserAccountDAOImpl;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.services.validate.UserAccountValidator;
import lv.javaguru.java2.services.validate.UserAccountValidatorImpl;

import java.util.Optional;

/**
 * Created by user on 05.04.2017.
 */
public class DeleteServiceImpl implements DeleteService {

    private UserAccountDAO userAccountDAO = new UserAccountDAOImpl();
    private UserAccountValidator userAccountValidator = new UserAccountValidatorImpl();

    @Override
    public void deleteUserAccount(Long accountId){
        Optional<UserAccount> userAccOpt = userAccountDAO.getById(accountId);
        if (!userAccOpt.isPresent()) {
            throw new IllegalArgumentException("User account not found by id = " + accountId);
        }
        userAccountDAO.delete(accountId);
        printMessage("User account with id " + accountId + " was deleted.");
    }

    public void printMessage(String message){
        System.out.println(message);
    }
}
