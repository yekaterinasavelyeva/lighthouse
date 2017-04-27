package lv.javaguru.java2.services.reservationServices;

import java.time.LocalDate;

/**
 * Created by user on 18.04.2017.
 */
public interface ProlongReservationService {
    void prolong(LocalDate dateTo, Long reservationId);
}
