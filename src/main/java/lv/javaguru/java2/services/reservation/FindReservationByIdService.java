package lv.javaguru.java2.services.reservation;

import lv.javaguru.java2.domain.Reservation;

/**
 * Created by user on 18.04.2017.
 */
public interface FindReservationByIdService {
    Reservation find(Long resourceId);
}
