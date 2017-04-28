package lv.javaguru.java2.services.reservation.validate.impls;

import lv.javaguru.java2.services.reservation.validate.InputValidator;
import lv.javaguru.java2.services.reservation.validate.exceptions.ReservationEndDateException;
import lv.javaguru.java2.services.reservation.validate.exceptions.ReservationStartDateException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by mobileqa on 28/04/17.
 */
@Component
public class InputValidatorImpl implements InputValidator {

    @Override
    public void validateStartDateInput(LocalDate dateFrom) {
        if (dateFrom == null) {
            throw new ReservationStartDateException("Start date cannot be empty.");
        }
    }

    @Override
    public void validateEndDateInput(LocalDate dateTo) {
        if (dateTo == null) {
            throw new ReservationEndDateException("End date cannot be empty.");
        }
    }
}
