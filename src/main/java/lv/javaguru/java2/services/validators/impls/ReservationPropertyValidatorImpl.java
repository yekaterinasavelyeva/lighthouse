package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.services.exceptions.ReservationPropertyException;
import lv.javaguru.java2.services.validators.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.*;

/**
 * Created by user on 09.04.2017.
 */
@Component
class ReservationPropertyValidatorImpl implements ReservationPropertyValidator {

    @Autowired
    private DataInputValidator dataInputValidator;
    @Autowired
    private DataExistValidator dataExistValidator;
    @Autowired
    private LibraryRuleValidator ruleValidator;

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
            dataInputValidator.validateStartDateInput(dateFrom);
            ruleValidator.validateReservationStartDateLimits(dateFrom);
        } catch (IllegalArgumentException e) {
            collectMessage(e.getMessage());
        }
    }

    private void validateEndDate(LocalDate dateTo) {
        try {
            dataInputValidator.validateEndDateInput(dateTo);
            ruleValidator.validateReservationEndDateLimits(dateTo);
        } catch (IllegalArgumentException e) {
            collectMessage(e.getMessage());
        }
    }

    private void validateResourceId(Long resourceId){
        try {
            dataInputValidator.validateResourceIdInput(resourceId);
            dataExistValidator.validateResourceIdExists(resourceId);
            ruleValidator.validateResourceReservationStatusWhenCreatingReservation(resourceId);
        } catch (IllegalArgumentException e) {
            collectMessage(e.getMessage());
        }
    }

    private void validateUserAccountId(Long userAccountId){
        try {
            dataInputValidator.validateUserAccountIdInput(userAccountId);
            dataExistValidator.validateResourceIdExists(userAccountId);
        } catch (IllegalArgumentException e) {
            collectMessage(e.getMessage());
        }
    }

    private void handleValidationResult() {
        String resultMessage = validationMessages.toString();
        if (!resultMessage.isEmpty()) {
            throw new ReservationPropertyException(resultMessage);
        }
    }

    private void collectMessage(String message) {
        validationMessages.append(message).append("\n");
    }
}
