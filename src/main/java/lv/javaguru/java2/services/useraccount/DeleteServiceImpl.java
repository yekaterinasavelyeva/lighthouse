package lv.javaguru.java2.services.useraccount;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.database.jdbc.ReservationDAOImpl;
import lv.javaguru.java2.database.jdbc.UserAccountDAOImpl;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.services.useraccount.validate.UserAccountIdValidator;
import lv.javaguru.java2.services.useraccount.validate.UserAccountIdValidatorImpl;

import java.util.Optional;

/**
 * Created by user on 05.04.2017.
 */
public class DeleteServiceImpl implements DeleteService {

    //TODO: Implement check if there are active reservations, then do not delete, throw exception

    private UserAccountDAO userAccountDAO = new UserAccountDAOImpl();
    private ReservationDAO reservationDAO = new ReservationDAOImpl();
    private UserAccountIdValidator userAccountIdValidator = new UserAccountIdValidatorImpl();

    public DeleteServiceImpl(UserAccountIdValidator validator, UserAccountDAO dao, ReservationDAO reservationDao){
        userAccountIdValidator = validator;
        userAccountDAO = dao;
        reservationDAO = reservationDao;
    }

    @Override
    public void deleteUserAccount(Long accountId){
        userAccountIdValidator.validate(accountId);
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
