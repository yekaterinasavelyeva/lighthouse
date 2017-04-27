package lv.javaguru.java2.services.resource;

import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.database.jdbc.ResourceDAOImpl;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.services.resource.validate.ResourceIdValidator;
import lv.javaguru.java2.services.resource.validate.impls.ResourceIdValidatorImpl;

import java.util.Optional;

/**
 * Created by user on 17.04.2017.
 */
public class FindResourceServiceImpl implements FindResourceService {

    private ResourceDAO resourceDAO = new ResourceDAOImpl();
    private ResourceIdValidator resourceIdValidator = new ResourceIdValidatorImpl();

    public FindResourceServiceImpl (ResourceIdValidator validator, ResourceDAO dao){
        resourceDAO = dao;
        resourceIdValidator = validator;
    }
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
