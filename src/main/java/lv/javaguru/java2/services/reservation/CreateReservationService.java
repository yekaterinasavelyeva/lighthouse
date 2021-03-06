package lv.javaguru.java2.services.reservation;

import lv.javaguru.java2.domain.Reservation;

import java.time.LocalDate;

/**
 * Created by user on 09.04.2017.
 */
public interface CreateReservationService {

    Reservation create(LocalDate dateFrom, LocalDate dateTo, Long accountID, Long resourceID);

}
