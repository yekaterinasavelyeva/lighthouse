package lv.javaguru.java2.services.resource.impls;

import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.services.resource.DeleteResourceService;
import lv.javaguru.java2.services.validators.DeleteResourceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by user on 17.04.2017.
 */
@Component
 class DeleteResourceServiceImpl implements DeleteResourceService {

    @Autowired
    @Qualifier("HibernateResourceDAO")
    private ResourceDAO resourceDAO;

    @Autowired
    private DeleteResourceValidator validator;

   @Override
   public void delete(Long resourceId){
       validator.validate(resourceId);
       resourceDAO.delete(resourceId);
       System.out.println("Resource with id " + resourceId + " was deleted.");
   }
}
