package lv.javaguru.java2.services.reservation;

import lv.javaguru.java2.domain.Reservation;

import java.util.List;

/**
 * Created by user on 18.04.2017.
 */
public interface FindReservationByUserAccountIdService {
    List<Reservation> find(Long accountId);
}
