package lv.javaguru.java2.services.reservation.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.FindReservationByResourceIdService;
import lv.javaguru.java2.services.validators.InputValidator;
import lv.javaguru.java2.services.validators.SearchValidator;
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
    private SearchValidator searchValidator;
    @Autowired
    private InputValidator inputValidator;

    @Override
    public List<Reservation> find(Long resourceId){
        inputValidator.validateResourceIdInput(resourceId);
        searchValidator.validateResourceIdExist(resourceId);
        searchValidator.validateReservationExistForResourceId(resourceId);
        return reservationDAO.getByResourceID(resourceId);
    }
}
