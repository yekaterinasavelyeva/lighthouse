package lv.javaguru.java2.services.reservationServices;

import lv.javaguru.java2.domain.Reservation;

/**
 * Created by user on 18.04.2017.
 */
public interface FindByReservationIdService {
    Reservation find(Long resourceId);
}
