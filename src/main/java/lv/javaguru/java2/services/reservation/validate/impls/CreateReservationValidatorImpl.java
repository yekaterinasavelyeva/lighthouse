package lv.javaguru.java2.services.reservation.validate.impls;

import lv.javaguru.java2.services.reservation.validate.*;
import lv.javaguru.java2.services.reservation.validate.exceptions.ReservationEndDateException;
import lv.javaguru.java2.services.reservation.validate.exceptions.CreateReservationException;
import lv.javaguru.java2.services.reservation.validate.exceptions.ReservationStartDateException;
import lv.javaguru.java2.services.resource.validate.ResourceIdValidator;
import lv.javaguru.java2.services.resource.validate.exceptions.ResourceIdException;
import lv.javaguru.java2.services.useraccount.validate.UserAccountIdValidator;
import lv.javaguru.java2.services.useraccount.validate.exceptions.UserAccountIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.*;

/**
 * Created by user on 09.04.2017.
 */
@Component
public class CreateReservationValidatorImpl implements CreateReservationValidator {

    @Autowired
    private InputValidator inputValidator;
    @Autowired
    private ReservationRuleValidator reservationRuleValidator;
    @Autowired
    private ResourceIdValidator resourceIdValidator;
    @Autowired
    private UserAccountIdValidator userAccountIdValidator;

    private StringBuilder validationMessages = new StringBuilder();

    @Override
    public void validate(LocalDate dateFrom, LocalDate dateTo, Long accountID, Long resourceID){
        validateStartDate(dateFrom);
        validateEndDate(dateTo);
        validateResourceId(resourceID);
        validateUserAccountId(accountID);
        handleValidationResult();
    }

    private void validateStartDate(LocalDate dateFrom) {
        try {
            inputValidator.validateStartDateInput(dateFrom);
        } catch (ReservationStartDateException e) {
            collectMessage(e.getMessage());
        }
        try {
            reservationRuleValidator.validateStartDateForNewReservation(dateFrom);
        } catch (ReservationStartDateException e) {
            collectMessage(e.getMessage());
        }
    }

    private void validateEndDate(LocalDate dateTo) {
        try {
            inputValidator.validateEndDateInput(dateTo);
        } catch (ReservationEndDateException e) {
            collectMessage(e.getMessage());
        }
        try {
            reservationRuleValidator.validateEndDateForNewReservation(dateTo);
        } catch (ReservationEndDateException e) {
            collectMessage(e.getMessage());
        }
    }

    private void validateResourceId(Long resourceId){
        try {
            resourceIdValidator.validate(resourceId);
        } catch (ResourceIdException e) {
            collectMessage(e.getMessage());
        }
        try {
            reservationRuleValidator.validateResourceIdForNewReservation(resourceId);
        } catch (IllegalArgumentException e) {
            collectMessage(e.getMessage());
        }
    }

    private void validateUserAccountId(Long userAccountId){
        try {
            userAccountIdValidator.validate(userAccountId);
        } catch (UserAccountIdException e) {
            collectMessage(e.getMessage());
        }
    }

    private void handleValidationResult() {
        String resultMessage = validationMessages.toString();
        if (!resultMessage.isEmpty()) {
            throw new CreateReservationException(resultMessage);
        }
    }

    private void collectMessage(String message) {
        validationMessages.append(message).append("\n");
    }
}
