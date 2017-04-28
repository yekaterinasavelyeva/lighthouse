package lv.javaguru.java2.services.reservation.validate.impls;

import lv.javaguru.java2.services.reservation.validate.InputValidator;
import lv.javaguru.java2.services.reservation.validate.ProlongReservationValidator;
import lv.javaguru.java2.services.reservation.validate.ReservationRuleValidator;
import lv.javaguru.java2.services.reservation.validate.SearchValidator;
import lv.javaguru.java2.services.reservation.validate.exceptions.ProlongReservationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by mobileqa on 28/04/17.
 */
@Component
public class ProlongReservationValidatorImpl implements ProlongReservationValidator {
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
            // todo new rule for "reservation must be opened to prolong it"
        } catch (IllegalArgumentException e) {
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
