package lv.javaguru.java2.services.reservationServices;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.database.jdbc.ReservationDAOImpl;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.resource.validate.ResourceIdValidator;
import lv.javaguru.java2.services.resource.validate.ResourceIdValidatorImpl;

import java.util.List;

/**
 * Created by user on 18.04.2017.
 */
public class FindByResourceIdServiceImpl implements FindByResourceIdService {
    private ReservationDAO reservationDAO = new ReservationDAOImpl();
    private ResourceIdValidator resourceIdValidator = new ResourceIdValidatorImpl();

    public FindByResourceIdServiceImpl (ResourceIdValidator validator, ReservationDAO dao){
        reservationDAO = dao;
        resourceIdValidator = validator;
    }
    @Override
    public List<Reservation> find(Long resourceId){
        resourceIdValidator.validate(resourceId);
        List<Reservation> reservations = reservationDAO.getByResourceID(resourceId);
        if (reservations.isEmpty()) {
            throw new IllegalArgumentException("Reservations were not found by resource id = " + resourceId);
        }
        return reservations;

    }
}
