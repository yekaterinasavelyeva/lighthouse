package lv.javaguru.java2.services.reservation.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.FindReservationByIdService;
import lv.javaguru.java2.services.validators.DataExistValidator;
import lv.javaguru.java2.services.validators.DataInputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by user on 18.04.2017.
 */
@Component
class FindReservationByIdServiceImpl implements FindReservationByIdService {
    @Autowired
    @Qualifier("HibernateReservationDAO")
    private ReservationDAO reservationDAO;
    @Autowired
    private DataInputValidator dataInputValidator;
    @Autowired
    private DataExistValidator dataExistValidator;

    public Reservation find(Long reservationId){
        dataInputValidator.validateReservationIdInput(reservationId);
        dataExistValidator.validateReservationIdExists(reservationId);
        return reservationDAO.getByID(reservationId).get();

    }
}
