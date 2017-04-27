package lv.javaguru.java2.services.reservation.validate;

import java.time.LocalDate;

/**
 * Created by user on 18.04.2017.
 */
public class ReservationDateToValidatorImpl implements ReservationDateToValidator {

    @Override
    public void validate(LocalDate dateTo){
        validateDateTo(dateTo);
        validateDateToHronology(dateTo);
    }

    private void validateDateTo(LocalDate dateTo){
        if (dateTo == null) {
            throw new IllegalArgumentException("Reservation date must be not empty!");
        }
    }

    private void validateDateToHronology(LocalDate dateTo){
        if (dateTo.isBefore(LocalDate.now().plusDays(7))||dateTo.isAfter(LocalDate.now().plusDays(30))) {
            throw new IllegalArgumentException("Reservation End Date must be set for no less " +
                    "than 7 and no more than 30 days from now!");

        }
    }
}
