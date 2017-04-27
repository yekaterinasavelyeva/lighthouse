package lv.javaguru.java2.services.reservationServices.validate.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservationServices.validate.ReservationUserAccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mobileqa on 27/04/17.
 */
@Component
public class ReservationUserAccountValidatorImpl implements ReservationUserAccountValidator {

    @Autowired
    ReservationDAO reservationDAO;
    @Override
    public void validate(Long userAccountID) {
        validateReservationExistenceByUserAccountID(userAccountID);
    }

    private void validateReservationExistenceByUserAccountID(Long userAccountID) {
        List<Reservation> reservations = reservationDAO.getByAccountID(userAccountID);
        if (reservations.isEmpty()) {
            throw new IllegalArgumentException("Reservations were not found by account id = " + userAccountID);
        }
    }
}