package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.services.validators.SearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Created by mobileqa on 27/04/17.
 */
@Component
class SearchValidatorImpl implements SearchValidator {


    @Autowired
    @Qualifier("HibernateReservationDAO")
    private ReservationDAO reservationDAO;

    @Autowired
    @Qualifier("HibernateResourceDAO")
    private ResourceDAO resourceDAO;

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
    public void validateReservationIdExist(Long reservationId) {
        Optional<Reservation> reservationFromDB = reservationDAO.getByID(reservationId);
        if (!reservationFromDB.isPresent()) {
            throw new IllegalArgumentException("Reservation not found by id = " + reservationId);
        }
    }

    @Override
    public void validateResourceIdExist(Long resourceId) {
        Optional<Resource> resourceFromDB = resourceDAO.getByID(resourceId);
        if (!resourceFromDB.isPresent()) {
            throw new IllegalArgumentException("Resource not found by id = " + resourceId);
        }
    }


}
