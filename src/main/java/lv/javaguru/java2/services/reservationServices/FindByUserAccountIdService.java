package lv.javaguru.java2.services.reservationServices;

import lv.javaguru.java2.domain.Reservation;

import java.util.List;

/**
 * Created by user on 18.04.2017.
 */
public interface FindByUserAccountIdService {
    List<Reservation> find(Long accountId);
}
