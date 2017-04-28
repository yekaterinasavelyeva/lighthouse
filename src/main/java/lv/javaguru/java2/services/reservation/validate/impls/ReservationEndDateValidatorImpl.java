package lv.javaguru.java2.services.reservation.validate.impls;

import lv.javaguru.java2.services.reservation.validate.ReservationEndDateValidator;
import lv.javaguru.java2.services.reservation.validate.exceptions.ReservationEndDateException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by user on 18.04.2017.
 */
@Component
public class ReservationEndDateValidatorImpl implements ReservationEndDateValidator {

    private static final LocalDate MIN_END_DATE = LocalDate.now().plusDays(7);
    private static final LocalDate MAX_END_DATE = LocalDate.now().plusDays(30);

    @Override
    public void validate(LocalDate dateTo){
        validateNotNull(dateTo);
        validateEndDateLimitation(dateTo);
    }

    private void validateNotNull(LocalDate dateTo){
        if (dateTo == null) {
            throw new ReservationEndDateException("End date cannot be empty.");
        }
    }

    private void validateEndDateLimitation(LocalDate dateTo){
        if (isLessThanMinLimit(dateTo) || isMoreThanMaxLimit(dateTo)) {
            throw new ReservationEndDateException("Reservation End Date must be set for no less " +
                    "than 7 and no more than 30 days from now!");
        }
    }

    private boolean isLessThanMinLimit(LocalDate dateTo) {
        return dateTo.isBefore(MIN_END_DATE);
    }

    private boolean isMoreThanMaxLimit(LocalDate dateTo) {
        return dateTo.isAfter(MAX_END_DATE);
    }
}
