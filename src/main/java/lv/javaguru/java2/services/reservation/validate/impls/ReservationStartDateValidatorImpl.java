package lv.javaguru.java2.services.reservation.validate.impls;

import lv.javaguru.java2.services.reservation.validate.ReservationStartDateValidator;
import lv.javaguru.java2.services.reservation.validate.exceptions.ReservationStartDateException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by mobileqa on 27/04/17.
 */
@Component
public class ReservationStartDateValidatorImpl implements ReservationStartDateValidator {

    private static final LocalDate MIN_START_DATE = LocalDate.now();
    private static final LocalDate MAX_START_DATE = LocalDate.now();

    @Override
    public void validate(LocalDate startDate) {
        validateNotNull(startDate);
        validateStartDateLimitation(startDate);
    }

    private void validateNotNull(LocalDate date) {
        if (date == null) {
            throw new ReservationStartDateException("Start date cannot be empty.");
        }
    }

    private void validateStartDateLimitation(LocalDate startDate) {
        if (isLessThanMinLimit(startDate) || isMoreThanMaxLimit(startDate)) {
            throw new ReservationStartDateException("Reservation Start Date must be set for today!");
        }
    }

    private boolean isLessThanMinLimit(LocalDate date) {
        return date.isBefore(MIN_START_DATE);
    }

    private boolean isMoreThanMaxLimit(LocalDate date) {
        return date.isAfter(MAX_START_DATE);
    }
}
