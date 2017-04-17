package lv.javaguru.java2.services.reservation.validate;

/**
 * Created by user on 17.04.2017.
 */
public class ReservationIdValidatorImpl implements ReservationIdValidator {

    public void validate(Long reservationId){
        validateReservationId(reservationId);
    }

    private  void validateReservationId(Long reservationID){
        if (reservationID == null || reservationID<0) {
            throw new IllegalArgumentException("Reservation ID must not be empty or less than 0");
        }
    }
}
