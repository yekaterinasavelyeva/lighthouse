package lv.javaguru.java2.services.validate;

import java.time.LocalDate;

/**
 * Created by user on 09.04.2017.
 */
public interface ReservationValidator {

    void validate(LocalDate dateFrom, LocalDate dateTo, Long libTicketID);

}
