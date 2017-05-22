package lv.javaguru.java2.services.reservation.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.ProlongReservationService;
import lv.javaguru.java2.services.validators.ProlongReservationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by mobileqa on 28/04/17.
 */
@Component
class ProlongReservationServiceImpl implements ProlongReservationService {
    @Autowired
    @Qualifier("HibernateReservationDAO")
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
