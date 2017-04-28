package lv.javaguru.java2.services.reservation.validate.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.validate.SearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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


}
