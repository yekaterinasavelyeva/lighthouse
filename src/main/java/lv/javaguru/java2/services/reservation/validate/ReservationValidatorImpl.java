package lv.javaguru.java2.services.reservation.validate;

import org.springframework.stereotype.Component;

import java.time.*;

/**
 * Created by user on 09.04.2017.
 */
@Component
public class ReservationValidatorImpl implements ReservationValidator {

    private StringBuilder validationErrors = new StringBuilder();

    @Override
    public void validate(LocalDate dateFrom, LocalDate dateTo, Long accountID, Long resourceID){
        validateDateFrom(dateFrom);
        validateDateTo(dateTo);
        validateResourceId(resourceID);
        validateAccountId(accountID);
        handleValidationErrors();
    }

    private void validateDateFrom(LocalDate dateFrom) {
        if (dateFrom == null) {
            validationErrors.append("Start date cannot be empty.\n");
        } else {
            validateDateFromHronology(dateFrom);
        }
    }

    private void validateDateFromHronology(LocalDate dateFrom) {
        if (dateFrom.isAfter(LocalDate.now())||dateFrom.isBefore(LocalDate.now())) {
            validationErrors.append("Reservation Start Date must be set for today!\n");
        }
    }

    private void validateDateTo(LocalDate dateTo){
        if (dateTo == null) {
            validationErrors.append("End date cannot be empty.\n");
        } else {
            validateDateToHronology(dateTo);
        }
    }

    private void validateDateToHronology(LocalDate dateTo) {
        if (dateTo.isBefore(LocalDate.now().plusDays(7))||dateTo.isAfter(LocalDate.now().plusDays(30))) {
            validationErrors.append("Reservation End Date must be set for no less " +
                    "than 7 and no more than 30 days from now!\n");
        }
    }

    private void validateResourceId(Long resourceID){
        if (resourceID == null) {
            validationErrors.append("Resource ID cannot be empty.\n");
        }
    }

    private void validateAccountId(Long accountID){
        if (accountID == null) {
            validationErrors.append("Account ID cannot be empty.\n");
        }
    }

    private void handleValidationErrors() {
        String errors = validationErrors.toString();
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(errors);
        }
    }
}
