package lv.javaguru.java2.services.reservation.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.FindReservationByResourceIdService;
import lv.javaguru.java2.services.reservation.validate.SearchValidator;
import lv.javaguru.java2.services.resource.validate.ResourceIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by user on 18.04.2017.
 */
@Component
public class FindReservationByResourceIdServiceImpl implements FindReservationByResourceIdService {
    @Autowired
    @Qualifier("HibernateReservationDAO")
    private ReservationDAO reservationDAO;
    @Autowired
    private ResourceIdValidator resourceIdValidator;
    @Autowired
    private SearchValidator searchValidator;

    @Override
    public List<Reservation> find(Long resourceId){
        resourceIdValidator.validate(resourceId); // TODO: 28/04/17 further improvement - to inputValidator + searchValidator
        searchValidator.validateReservationExistForResourceId(resourceId);
        return reservationDAO.getByResourceID(resourceId);
    }
}
