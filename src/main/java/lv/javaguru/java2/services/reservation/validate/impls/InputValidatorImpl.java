package lv.javaguru.java2.services.reservation.validate.impls;

import lv.javaguru.java2.services.reservation.validate.InputValidator;
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
            throw new IllegalArgumentException("Start date cannot be empty.");
        }
    }

    @Override
    public void validateEndDateInput(LocalDate dateTo) {
        if (dateTo == null) {
            throw new IllegalArgumentException("End date cannot be empty.");
        }
    }

    @Override
    public void validateReservationIdInput(Long reservationId) {
        if (reservationId == null || reservationId<0) {
            throw new IllegalArgumentException("Reservation ID cannot be null or negative");
        }
    }
}