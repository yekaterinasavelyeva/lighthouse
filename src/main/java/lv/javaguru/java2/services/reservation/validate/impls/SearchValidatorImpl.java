package lv.javaguru.java2.services.reservation.validate.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.validate.SearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mobileqa on 27/04/17.
 */
@Component
public class SearchValidatorImpl implements SearchValidator {

    @Autowired
    ReservationDAO reservationDAO;

    @Override
    public void validateReservationExistForResourceId(Long resourceId) {
        List<Reservation> reservations = reservationDAO.getByResourceID(resourceId);
        if (reservations.isEmpty()) {
            throw new IllegalArgumentException("Reservations were not found for resource id = " + resourceId);
        }
    }

    @Override
    public void validateReservationExistForUserAccountID(Long userAccountID) {
        List<Reservation> reservations = reservationDAO.getByAccountID(userAccountID);
        if (reservations.isEmpty()) {
            throw new IllegalArgumentException("Reservations were not found by account id = " + userAccountID);
        }
    }

}
