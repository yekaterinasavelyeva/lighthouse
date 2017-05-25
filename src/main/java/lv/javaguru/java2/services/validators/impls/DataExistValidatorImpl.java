package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.services.validators.DataExistValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Created by mobileqa on 27/04/17.
 */
@Component
class DataExistValidatorImpl implements DataExistValidator {


    @Autowired
    @Qualifier("HibernateReservationDAO")
    private ReservationDAO reservationDAO;

    @Autowired
    @Qualifier("HibernateResourceDAO")
    private ResourceDAO resourceDAO;

    @Autowired
    @Qualifier("HibernateUserAccountDAO")
    private UserAccountDAO userAccountDAO;

    @Override
    public void validateReservationExistForResourceId(Long resourceId) {
        List<Reservation> reservations = reservationDAO.getByResourceID(resourceId);
        if (reservations.isEmpty()) {
            throw new IllegalArgumentException("Reservations were not found for resource id = " + resourceId);
        }
    }

    @Override
    public void validateReservationExistForUserAccountId(Long userAccountID) {
        List<Reservation> reservations = reservationDAO.getByAccountID(userAccountID);
        if (reservations.isEmpty()) {
            throw new IllegalArgumentException("Reservations were not found by account id = " + userAccountID);
        }
    }

    @Override
    public void validateReservationIdExists(Long reservationId) {
        Optional<Reservation> reservationFromDB = reservationDAO.getByID(reservationId);
        if (!reservationFromDB.isPresent()) {
            throw new IllegalArgumentException("Reservation not found by id = " + reservationId);
        }
    }

    @Override
    public void validateResourceIdExists(Long resourceId) {
        Optional<Resource> resourceFromDB = resourceDAO.getByID(resourceId);
        if (!resourceFromDB.isPresent()) {
            throw new IllegalArgumentException("Resource not found by id = " + resourceId);
        }
    }

    @Override
    public void validateUserAccountIdExists(Long userAccountId) {
        Optional<UserAccount> userAccountFromDB = userAccountDAO.getById(userAccountId);
        if (!userAccountFromDB.isPresent()) {
            throw new IllegalArgumentException("User account not found by id = " + userAccountId);
        }
    }


}
