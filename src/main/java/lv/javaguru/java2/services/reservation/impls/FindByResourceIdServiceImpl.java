package lv.javaguru.java2.services.reservation.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.FindByResourceIdService;
import lv.javaguru.java2.services.reservation.validate.ReservationForResourceValidator;
import lv.javaguru.java2.services.resource.validate.ResourceIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by user on 18.04.2017.
 */
@Component
public class FindByResourceIdServiceImpl implements FindByResourceIdService {
    @Autowired
    private ReservationDAO reservationDAO;
    @Autowired
    private ResourceIdValidator resourceIdValidator;
    @Autowired
    private ReservationForResourceValidator reservationForResourceValidator;

    @Override
    public List<Reservation> find(Long resourceId){
        resourceIdValidator.validate(resourceId);
        reservationForResourceValidator.validateReservationExistForResourceId(resourceId);
        return reservationDAO.getByResourceID(resourceId);
    }
}
