package lv.javaguru.java2.services.reservation.validate;

import java.time.LocalDate;

/**
 * Created by vbarbasins on 2017.04.28..
 */
public interface ReservationRuleValidator {

    void validateResourceIdForNewReservation(Long resourceId);

    void validateStartDateForReservation(LocalDate dateFrom);

    void validateEndDateForReservation(LocalDate dateTo);
}
