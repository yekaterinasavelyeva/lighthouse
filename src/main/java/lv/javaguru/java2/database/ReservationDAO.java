package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Reservation;

import java.util.List;
import java.util.Optional;

/**
 * Created by VBarbasins on 30/03/17.
 */
public interface ReservationDAO {

    Reservation save(Reservation reservation);

    Optional<Reservation> getByID(Long reservationID);

    void update(Reservation reservation);

    List<Reservation> getByLibTicketID(Long libTicketID);

    List<Reservation> getByResourceID(Long resourceID);

    List<Reservation> getAll();

}
