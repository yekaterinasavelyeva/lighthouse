package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.services.validators.InputValidator;
import lv.javaguru.java2.services.validators.ProlongReservationValidator;
import lv.javaguru.java2.services.validators.ReservationRuleValidator;
import lv.javaguru.java2.services.validators.SearchValidator;
import lv.javaguru.java2.services.exceptions.ProlongReservationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by mobileqa on 28/04/17.
 */
@Component
class ProlongReservationValidatorImpl implements ProlongReservationValidator {
    @Autowired
    private InputValidator inputValidator;
    @Autowired
    private SearchValidator searchValidator;
    @Autowired
    private ReservationRuleValidator ruleValidator;

    private StringBuilder validationMessages = new StringBuilder();

    @Override
    public void validate(Long reservationId, LocalDate dateTo) {
        validateReservationId(reservationId);
        validateDateTo(dateTo);
        handleValidationResult();
    }

    private void validateReservationId(Long reservationId) {
        try {
            inputValidator.validateReservationIdInput(reservationId);
            searchValidator.validateReservationIdExist(reservationId);
            ruleValidator.validateReservationStatusForProlongation(reservationId);        } catch (IllegalArgumentException e) {
            collectMessage(e.getMessage());
        }
    }

    private void validateDateTo(LocalDate dateTo) {
        try {
            inputValidator.validateEndDateInput(dateTo);
            ruleValidator.validateEndDateForReservation(dateTo);
        } catch (IllegalArgumentException e) {
            collectMessage(e.getMessage());
        }
    }

    private void handleValidationResult() {
        String resultMessage = validationMessages.toString();
        if (!resultMessage.isEmpty()) {
            throw new ProlongReservationException(resultMessage);
        }
    }

    private void collectMessage(String message) {
        validationMessages.append(message).append("\n");
    }
}
