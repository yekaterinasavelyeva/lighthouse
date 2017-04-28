package lv.javaguru.java2.services.reservation.validate.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.services.reservation.validate.ReservationRuleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vbarbasins on 2017.04.28..
 */
@Component
public class ReservationRuleValidatorImpl implements ReservationRuleValidator {
    @Autowired
    ReservationDAO reservationDAO;

    @Override
    public void validateReservationForResourceMustBeClosed(Long resourceId) {
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
