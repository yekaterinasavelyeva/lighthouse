package lv.javaguru.java2.services.reservation.validate.validatorImpls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.validate.ReservationIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by user on 17.04.2017.
 */
@Component
public class ReservationIdValidatorImpl implements ReservationIdValidator {

    @Autowired
    private ReservationDAO reservationDAO;

    public void validate(Long reservationId){
        validateNotNullAndNotNegative(reservationId);
        validateExistence(reservationId);
    }

    private void validateExistence(Long reservationId) {
        Optional<Reservation> reservationFromDB = reservationDAO.getByID(reservationId);
        if (!reservationFromDB.isPresent()) {
            throw new IllegalArgumentException("Reservation not found by id = " + reservationId);
        }
    }

    private  void validateNotNullAndNotNegative(Long reservationID){
        if (reservationID == null || reservationID<0) {
            throw new IllegalArgumentException("Reservation ID cannot be null or negative");
        }
    }
}
