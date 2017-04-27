package lv.javaguru.java2.services.reservation.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.FindByReservationIdService;
import lv.javaguru.java2.services.reservation.validate.ReservationIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by user on 18.04.2017.
 */
@Component
public class FindByReservationIdServiceImpl implements FindByReservationIdService {
    @Autowired
    private ReservationDAO reservationDAO;
    @Autowired
    private ReservationIdValidator reservationIdValidator;

    public Reservation find(Long reservationId){
        reservationIdValidator.validate(reservationId);
        return reservationDAO.getByID(reservationId).get();

    }
}
