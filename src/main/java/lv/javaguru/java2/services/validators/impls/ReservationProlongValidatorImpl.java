package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.services.validators.*;
import lv.javaguru.java2.services.exceptions.ReservationProlongException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by mobileqa on 28/04/17.
 */
@Component
class ReservationProlongValidatorImpl implements ReservationProlongValidator {
    @Autowired
    private DataInputValidator dataInputValidator;
    @Autowired
    private DataExistValidator dataExistValidator;
    @Autowired
    private LibraryRuleValidator ruleValidator;

    private StringBuilder validationMessages = new StringBuilder();

    @Override
    public void validate(Long reservationId, LocalDate dateTo) {
        validateReservationId(reservationId);
        validateDateTo(dateTo);
        handleValidationResult();
    }

    private void validateReservationId(Long reservationId) {
        try {
            dataInputValidator.validateReservationIdInput(reservationId);
            dataExistValidator.validateReservationIdExists(reservationId);
            ruleValidator.validateReservationStatusWhenProlongingIt(reservationId);        } catch (IllegalArgumentException e) {
            collectMessage(e.getMessage());
        }
    }

    private void validateDateTo(LocalDate dateTo) {
        try {
            dataInputValidator.validateEndDateInput(dateTo);
            ruleValidator.validateReservationEndDateLimits(dateTo);
        } catch (IllegalArgumentException e) {
            collectMessage(e.getMessage());
        }
    }

    private void handleValidationResult() {
        String resultMessage = validationMessages.toString();
        if (!resultMessage.isEmpty()) {
            throw new ReservationProlongException(resultMessage);
        }
    }

    private void collectMessage(String message) {
        validationMessages.append(message).append("\n");
    }
}
