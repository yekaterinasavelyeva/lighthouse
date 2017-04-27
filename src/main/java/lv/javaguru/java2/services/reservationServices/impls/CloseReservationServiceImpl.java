package lv.javaguru.java2.services.reservationServices.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.services.reservationServices.CloseReservationService;
import lv.javaguru.java2.services.reservationServices.validate.ReservationIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by user on 17.04.2017.
 */
@Component
public class CloseReservationServiceImpl implements CloseReservationService {

    @Autowired
    private ReservationDAO reservationDAO;
    @Autowired
    private ReservationIdValidator validator;

    public void closeByID(Long reservationId){
        validator.validate(reservationId);
        Reservation reservation = getReservationFromDB(reservationId);
        closeReservationAndUpdateDB(reservation);
    }

    private Reservation getReservationFromDB(Long reservationId) {
        Optional<Reservation> reservationFromDB = reservationDAO.getByID(reservationId);
        return reservationFromDB.get();
    }

    private void closeReservationAndUpdateDB(Reservation reservation) {
        reservation.setStatus(ReservationStatus.CLOSED);
        reservationDAO.update(reservation);
    }
}
