package lv.javaguru.java2.services.resource;

import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.database.jdbc.ResourceDAOImpl;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.domain.ResourceType;
import lv.javaguru.java2.services.resource.validate.ResourceIdValidator;
import lv.javaguru.java2.services.resource.validate.impls.ResourceIdValidatorImpl;
import lv.javaguru.java2.services.resource.validate.ResourceValidator;
import lv.javaguru.java2.services.resource.validate.impls.ResourceValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by user on 17.04.2017.
 */
@Component
public class EditResourceServiceImpl implements EditResourceService {

    @Autowired
    private ResourceDAO resourceDAO;
    @Autowired
    private ResourceValidator resourceValidator;
    @Autowired
    private ResourceIdValidator resourceIdValidator;

    @Override
    public void edit(Long resourceId, ResourceType type, String title,
                     String author, int releaseYear){
        resourceIdValidator.validate(resourceId);
        Optional<Resource> resourceOpt = resourceDAO.getByID(resourceId);
        if (!resourceOpt.isPresent()) {
            throw new IllegalArgumentException("Resource not found by id = " + resourceId);
        }

        resourceValidator.validate(type, title, author, releaseYear);

        Resource resource = resourceOpt.get();
        resource.setResourceType(type);
        resource.setTitle(title);
        resource.setAuthor(author);
        resource.setReleaseYear(releaseYear);
        resourceDAO.update(resource);

    }
}
