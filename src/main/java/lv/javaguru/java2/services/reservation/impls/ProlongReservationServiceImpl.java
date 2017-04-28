package lv.javaguru.java2.services.reservation.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.ProlongReservationService;
import lv.javaguru.java2.services.reservation.validate.ProlongReservationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by mobileqa on 28/04/17.
 */
@Component
public class ProlongReservationServiceImpl implements ProlongReservationService {
    @Autowired
    private ReservationDAO reservationDAO;
    @Autowired
    private ProlongReservationValidator validator;

    public void prolongReservationToDate(Long reservationId, LocalDate dateTo) {
        validator.validate(reservationId, dateTo);
        Reservation reservation = reservationDAO.getByID(reservationId).get();
        reservation.setDateTo(dateTo);
        reservationDAO.update(reservation);
    }

}
