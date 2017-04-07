package lv.javaguru.java2.services;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.database.jdbc.UserAccountDAOImpl;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.domain.UserAccountState;
import lv.javaguru.java2.services.validate.UserAccountValidator;
import lv.javaguru.java2.services.validate.UserAccountValidatorImpl;

import java.util.List;
import java.util.Optional;

/**
 * Created by user on 26.03.2017.
 */
public class UserAccountServiceImpl implements UserAccountService{

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
        userAccountDAO.update(account);
    }

    @Override
    public void deleteUserAccount(Long accountId){
        Optional<UserAccount> userAccOpt = userAccountDAO.getById(accountId);
        if (!userAccOpt.isPresent()) {
            throw new IllegalArgumentException("User account not found by id = " + accountId);
        }
        userAccountDAO.delete(accountId);
        printMessage("User account with id " + accountId + " was deleted.");
    }

    @Override
    public List<UserAccount> getAllUsersList(){
        List<UserAccount> accounts = userAccountDAO.getAll();
                return accounts;
    }

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
