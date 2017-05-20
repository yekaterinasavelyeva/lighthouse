package lv.javaguru.java2.services.resource;

import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.database.jdbc.ResourceDAOImpl;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.services.resource.validate.ResourceIdValidator;
import lv.javaguru.java2.services.resource.validate.impls.ResourceIdValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by user on 17.04.2017.
 */
@Component
public class FindResourceServiceImpl implements FindResourceService {

    @Autowired
    @Qualifier("HibernateResourceDAO")
    private ResourceDAO resourceDAO;

    @Autowired
    private ResourceIdValidator resourceIdValidator;

    @Override
    public Resource findResource(Long resourceId){
        resourceIdValidator.validate(resourceId);
        Optional<Resource> resourceOpt = resourceDAO.getByID(resourceId);
        if (!resourceOpt.isPresent()) {
            throw new IllegalArgumentException("Resource not found by id = " + resourceId);
        }
        Resource resource = resourceOpt.get();
        return resource;
    }
}
