package lv.javaguru.java2.services.reservation.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.services.reservation.CloseReservationService;
import lv.javaguru.java2.services.validators.DataExistValidator;
import lv.javaguru.java2.services.validators.DataInputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by user on 17.04.2017.
 */
@Component
class CloseReservationServiceImpl implements CloseReservationService {

    @Autowired
    @Qualifier("HibernateReservationDAO")
    private ReservationDAO reservationDAO;
    @Autowired
    private DataInputValidator dataInputValidator;
    @Autowired
    private DataExistValidator dataExistValidator;

    public void closeByID(Long reservationId){
        dataInputValidator.validateReservationIdInput(reservationId);
        dataExistValidator.validateReservationIdExists(reservationId);
        Reservation reservation = reservationDAO.getByID(reservationId).get();
        reservation.setStatus(ReservationStatus.CLOSED);
        reservationDAO.update(reservation);
    }
}
