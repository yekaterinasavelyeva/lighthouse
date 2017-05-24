package lv.javaguru.java2.services.validators;

import java.time.LocalDate;

/**
 * Created by user on 09.04.2017.
 */
public interface ReservationPropertyValidator {

    void validate(LocalDate dateFrom, LocalDate dateTo, Long accountID, Long resourceID);

}
