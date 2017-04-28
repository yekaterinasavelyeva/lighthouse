package lv.javaguru.java2.services.reservation.validate;

import java.time.LocalDate;

/**
 * Created by mobileqa on 27/04/17.
 */
public interface ReservationStartDateValidator {

    void validate(LocalDate startDate);
}
