package lv.javaguru.java2.services.reservation.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.services.reservation.CloseReservationService;
import lv.javaguru.java2.services.reservation.validate.InputValidator;
import lv.javaguru.java2.services.reservation.validate.SearchValidator;
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
    private InputValidator inputValidator;
    @Autowired
    private SearchValidator searchValidator;

    public void closeByID(Long reservationId){
        inputValidator.validateReservationIdInput(reservationId);
        searchValidator.validateReservationIdExist(reservationId);
        Reservation reservation = reservationDAO.getByID(reservationId).get();
        reservation.setStatus(ReservationStatus.CLOSED);
        reservationDAO.update(reservation);
    }
}
