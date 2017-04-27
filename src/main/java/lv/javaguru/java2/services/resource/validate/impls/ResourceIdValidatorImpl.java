package lv.javaguru.java2.services.resource.validate.impls;

import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.services.resource.validate.ResourceIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by user on 17.04.2017.
 */
@Component
public class ResourceIdValidatorImpl implements ResourceIdValidator {

    @Autowired
    ResourceDAO resourceDAO;

    public void validate(Long resourceID){
        validateNotNullAndNotNegative(resourceID);
        validateExistence(resourceID);
    }

    private  void validateNotNullAndNotNegative(Long resourceId){
        if (resourceId == null || resourceId<0) {
            throw new IllegalArgumentException("Resource ID must not be empty or less than 0");
        }
    }

    private void validateExistence(Long resourceID) {
        Optional<Resource> resourceFromDB = resourceDAO.getByID(resourceID);
        if (!resourceFromDB.isPresent()) {
            throw new IllegalArgumentException("Resource not found by id = " + resourceID);
        }

    }
}