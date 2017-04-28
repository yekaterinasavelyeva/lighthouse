package lv.javaguru.java2.services.reservation.validate.impls;

import lv.javaguru.java2.services.reservation.validate.*;
import lv.javaguru.java2.services.reservation.validate.exceptions.CreateReservationException;
import lv.javaguru.java2.services.resource.validate.ResourceIdValidator;
import lv.javaguru.java2.services.useraccount.validate.UserAccountIdValidator;
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
    private ReservationRuleValidator ruleValidator;
    @Autowired
    private ResourceIdValidator resourceIdValidator;
    @Autowired
    private UserAccountIdValidator userAccountIdValidator;

    private StringBuilder validationMessages = new StringBuilder();

    @Override
    public void validate(LocalDate dateFrom, LocalDate dateTo, Long resourceID, Long accountID){
        validateStartDate(dateFrom);
        validateEndDate(dateTo);
        validateResourceId(resourceID);
        validateUserAccountId(accountID);
        handleValidationResult();
    }

    private void validateStartDate(LocalDate dateFrom) {
        try {
            inputValidator.validateStartDateInput(dateFrom);
            ruleValidator.validateStartDateForReservation(dateFrom);
        } catch (IllegalArgumentException e) {
            collectMessage(e.getMessage());
        }
    }

    private void validateEndDate(LocalDate dateTo) {
        try {
            inputValidator.validateEndDateInput(dateTo);
            ruleValidator.validateEndDateForReservation(dateTo);
        } catch (IllegalArgumentException e) {
            collectMessage(e.getMessage());
        }
    }

    private void validateResourceId(Long resourceId){
        try {
            resourceIdValidator.validate(resourceId);
            ruleValidator.validateResourceIdForNewReservation(resourceId);
        } catch (IllegalArgumentException e) {
            collectMessage(e.getMessage());
        }
    }

    private void validateUserAccountId(Long userAccountId){
        try {
            userAccountIdValidator.validate(userAccountId);
        } catch (IllegalArgumentException e) {
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
