package lv.javaguru.java2.services.reservation.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.FindReservationByResourceIdService;
import lv.javaguru.java2.services.validators.DataExistValidator;
import lv.javaguru.java2.services.validators.DataInputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by user on 18.04.2017.
 */
@Component
class FindReservationByResourceIdServiceImpl implements FindReservationByResourceIdService {
    @Autowired
    @Qualifier("HibernateReservationDAO")
    private ReservationDAO reservationDAO;
    @Autowired
    private DataExistValidator dataExistValidator;
    @Autowired
    private DataInputValidator dataInputValidator;

    @Override
    public List<Reservation> find(Long resourceId){
        dataInputValidator.validateResourceIdInput(resourceId);
        dataExistValidator.validateResourceIdExists(resourceId);
        dataExistValidator.validateReservationExistForResourceId(resourceId);
        return reservationDAO.getByResourceID(resourceId);
    }
}
