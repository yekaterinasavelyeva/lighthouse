package lv.javaguru.java2.services.validators;

import java.time.LocalDate;

/**
 * Created by mobileqa on 28/04/17.
 */
public interface ReservationProlongValidator {

    void validate(Long reservationId, LocalDate dateTo);
}
