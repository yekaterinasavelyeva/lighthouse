package lv.javaguru.java2.services.useraccount;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.database.jdbc.ReservationDAOImpl;
import lv.javaguru.java2.database.jdbc.UserAccountDAOImpl;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.services.useraccount.validate.UserAccountIdValidator;
import lv.javaguru.java2.services.useraccount.validate.validatorsImpls.UserAccountIdValidatorImpl;

import java.util.List;
import java.util.Optional;

/**
 * Created by user on 05.04.2017.
 */
public class DeleteUserAccountServiceImpl implements DeleteUserAccountService {

    //TODO: Implement check if there are active reservations, then do not delete, throw exception

    private UserAccountDAO userAccountDAO = new UserAccountDAOImpl();
    private ReservationDAO reservationDAO = new ReservationDAOImpl();
    private UserAccountIdValidator userAccountIdValidator = new UserAccountIdValidatorImpl();

    public DeleteUserAccountServiceImpl(UserAccountIdValidator validator, UserAccountDAO dao, ReservationDAO reservationDao){
        userAccountIdValidator = validator;
        userAccountDAO = dao;
        reservationDAO = reservationDao;
    }

    @Override
    public void deleteUserAccount(Long accountId){
        userAccountIdValidator.validate(accountId);
        validateUserAccountExist(accountId);
        validateUserAccountDoesntHaveOpenedReservations(accountId);
        userAccountDAO.delete(accountId);
        printMessage("User account with id " + accountId + " was deleted.");
    }

    private void validateUserAccountExist(Long accountId) {
        Optional<UserAccount> userAccOpt = userAccountDAO.getById(accountId);
        if (!userAccOpt.isPresent()) {
            throw new IllegalArgumentException("User account not found by id = " + accountId);
        }
    }

    private void validateUserAccountDoesntHaveOpenedReservations(Long accountID) {
        List<Reservation> reservationsFromDB = reservationDAO.getByAccountID(accountID);
        boolean thereAreOpenedReservations = reservationsFromDB.stream()
                .anyMatch(reservation -> reservation.getStatus().equals(ReservationStatus.OPEN));
        if (thereAreOpenedReservations) {
            throw new IllegalArgumentException("There are opened reservations for this account.");
        }
    }

    public void printMessage(String message){
        System.out.println(message);
    }
}
