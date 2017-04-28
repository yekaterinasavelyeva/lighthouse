package lv.javaguru.java2.services.reservation.validate.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.services.reservation.validate.ReservationForResourceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mobileqa on 27/04/17.
 */
@Component
public class ReservationForResourceValidatorImpl implements ReservationForResourceValidator {

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
    public void validateReservationClosedForResourceId(Long resourceId) {
        boolean isAnyOpenedReservation = checkForOpenedReservationsInDBByResourceId(resourceId);
        if (isAnyOpenedReservation) {
            throw new IllegalArgumentException("There are opened reservations for this resource. Cannot create new reservation.");
        }
    }

    private boolean checkForOpenedReservationsInDBByResourceId(Long resourceId) {
        return reservationDAO.getByResourceID(resourceId)
                .stream()
                .anyMatch(reservation
                        -> reservation
                        .getStatus()
                        .equals(ReservationStatus.OPEN));
    }
}
