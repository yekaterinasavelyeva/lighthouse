package lv.javaguru.java2.services.resource;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.database.jdbc.ReservationDAOImpl;
import lv.javaguru.java2.database.jdbc.ResourceDAOImpl;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.services.resource.validate.ResourceIdValidator;
import lv.javaguru.java2.services.resource.validate.ResourceIdValidatorImpl;

import java.util.List;
import java.util.Optional;

/**
 * Created by user on 17.04.2017.
 */
public class DeleteResourceServiceImpl implements DeleteResourceService {

    private ResourceDAO resourceDAO = new ResourceDAOImpl();
    ReservationDAO reservationDAO = new ReservationDAOImpl();
    private ResourceIdValidator resourceIdValidator = new ResourceIdValidatorImpl();

    public DeleteResourceServiceImpl(ResourceIdValidator validator, ResourceDAO dao, ReservationDAO reservationDao){
        resourceIdValidator = validator;
        resourceDAO = dao;
        reservationDAO = reservationDao;
    }

   @Override
   public void deleteResource(Long resourceId){
       resourceIdValidator.validate(resourceId);
       validateResourceExist(resourceId);
       validateReservationsForResourceAreClosed(resourceId);
       resourceDAO.delete(resourceId);
       printMessage("Resource with id " + resourceId + " was deleted.");
   }

    private void validateResourceExist(Long resourceId) {
        Optional<Resource> resourceFromDB = resourceDAO.getByID(resourceId);
        if (!resourceFromDB.isPresent()) {
            throw new IllegalArgumentException("Resource not found by id = " + resourceId);
        }
    }

    private void validateReservationsForResourceAreClosed(Long resourceId) {
        List<Reservation> reservationsFromDB = reservationDAO.getByResourceID(resourceId);
        boolean thereAreOpenedReservations = false;
        thereAreOpenedReservations = reservationsFromDB.stream()
                .anyMatch(reservation -> reservation.getStatus().equals(ReservationStatus.OPEN));
        if (thereAreOpenedReservations) {
            throw new IllegalArgumentException("There are opened reservations for this resource");
        }
    }

    public void printMessage(String message){
       System.out.println(message);
    }

}