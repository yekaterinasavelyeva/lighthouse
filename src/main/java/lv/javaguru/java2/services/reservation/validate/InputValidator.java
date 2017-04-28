package lv.javaguru.java2.services.reservation.validate;

import java.time.LocalDate;

/**
 * Created by mobileqa on 28/04/17.
 */
public interface InputValidator {

    void validateStartDateInput(LocalDate dateFrom);
    void validateEndDateInput(LocalDate dateTo);
}
