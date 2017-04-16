package lv.javaguru.java2.services.reservation.validate;

import java.time.*;

/**
 * Created by user on 09.04.2017.
 */
public class ReservationValidatorImpl implements ReservationValidator {

    @Override
    public void validate(LocalDate dateFrom, LocalDate dateTo, Long accountID){
        validateDateFrom(dateFrom);
        validateDateFromHronology(dateFrom);
        validateDateTo(dateTo);
        validateDateToHronology(dateTo);
        validateAccountId(accountID);

    }

    private void validateDateFrom(LocalDate dateFrom) {
       checkIfDatesAreNull(dateFrom);
    }

    private void validateDateTo(LocalDate dateTo){
        checkIfDatesAreNull(dateTo);
    }

    private void validateDateFromHronology(LocalDate dateFrom){
        if (dateFrom.isAfter(LocalDate.now())||dateFrom.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Reservation Start Date must be set for today!");
        }
    }

    private void validateDateToHronology(LocalDate dateTo){
        if (dateTo.isBefore(LocalDate.now().plusDays(7))||dateTo.isAfter(LocalDate.now().plusDays(30))) {
            throw new IllegalArgumentException("Reservation End Date must be set for no less " +
                    "than 7 and no more than 30 days from now!");

        }
    }

    private void validateAccountId(Long accountID){
        if (accountID == null) {
            throw new IllegalArgumentException("Account ID must be not empty!");
        }
    }


    private void checkIfDatesAreNull(LocalDate date){
        if (date == null) {
            throw new IllegalArgumentException("Reservation dates must be not empty!");
        }
    }

}
