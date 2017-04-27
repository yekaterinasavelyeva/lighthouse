package lv.javaguru.java2.services.reservation.validate;

import java.time.LocalDate;

/**
 * Created by user on 18.04.2017.
 */
public interface ReservationEndDateValidator {
    void validate(LocalDate dateTo);
}
