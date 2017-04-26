package lv.javaguru.java2.services.reservation;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.database.jdbc.ReservationDAOImpl;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.useraccount.validate.UserAccountIdValidator;
import lv.javaguru.java2.services.useraccount.validate.UserAccountIdValidatorImpl;

import java.util.List;

/**
 * Created by user on 18.04.2017.
 */
public class FindByAccountIdServiceImpl implements FindByAccountIdService {
    private ReservationDAO reservationDAO = new ReservationDAOImpl();
    private UserAccountIdValidator accountValidator = new UserAccountIdValidatorImpl();

    public FindByAccountIdServiceImpl (UserAccountIdValidator validator, ReservationDAO dao){
        reservationDAO = dao;
        accountValidator = validator;
    }
    @Override
    public List<Reservation> find(Long accountId){
        accountValidator.validate(accountId);
        List<Reservation> reservations = reservationDAO.getByAccountID(accountId);
        if (reservations.isEmpty()) {
            throw new IllegalArgumentException("Reservations were not found by account id = " + accountId);
        }
        return reservations;

    }
}
