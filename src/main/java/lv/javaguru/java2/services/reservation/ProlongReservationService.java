package lv.javaguru.java2.services.reservation;

import java.time.LocalDate;

/**
 * Created by mobileqa on 28/04/17.
 */
public interface ProlongReservationService {

    void prolongReservationToDate(Long reservationId, LocalDate dateTo);
}
