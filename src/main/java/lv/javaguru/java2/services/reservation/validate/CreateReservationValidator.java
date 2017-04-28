package lv.javaguru.java2.services.reservation.validate;

import java.time.LocalDate;

/**
 * Created by user on 09.04.2017.
 */
public interface CreateReservationValidator {

    void validate(LocalDate dateFrom, LocalDate dateTo, Long accountID, Long resourceID);

}
