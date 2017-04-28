package lv.javaguru.java2.services.reservation.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.FindReservationByIdService;
import lv.javaguru.java2.services.reservation.validate.InputValidator;
import lv.javaguru.java2.services.reservation.validate.SearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by user on 18.04.2017.
 */
@Component
public class FindReservationByIdServiceImpl implements FindReservationByIdService {
    @Autowired
    private ReservationDAO reservationDAO;
    @Autowired
    private InputValidator inputValidator;
    @Autowired
    private SearchValidator searchValidator;

    public Reservation find(Long reservationId){
        inputValidator.validateReservationIdInput(reservationId);
        searchValidator.validateReservationIdExist(reservationId);
        return reservationDAO.getByID(reservationId).get();

    }
}
