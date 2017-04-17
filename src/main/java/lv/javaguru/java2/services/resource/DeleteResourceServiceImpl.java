package lv.javaguru.java2.services.resource;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.database.jdbc.ReservationDAOImpl;
import lv.javaguru.java2.database.jdbc.ResourceDAOImpl;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.services.resource.validate.ResourceIdValidator;
import lv.javaguru.java2.services.resource.validate.ResourceIdValidatorImpl;

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
       Optional<Resource> userAccOpt = resourceDAO.getByID(resourceId);
       if (!userAccOpt.isPresent()) {
           throw new IllegalArgumentException("Resource not found by id = " + resourceId);
       }
       resourceDAO.delete(resourceId);
       printMessage("Resource with id " + resourceId + " was deleted.");
   }

    public void printMessage(String message){
       System.out.println(message);
    }

}
