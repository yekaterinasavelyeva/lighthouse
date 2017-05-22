package lv.javaguru.java2.services.resource.impls;

import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.domain.ResourceType;
import lv.javaguru.java2.services.resource.EditResourceService;
import lv.javaguru.java2.services.validators.ResourceIdValidator;
import lv.javaguru.java2.services.validators.CreateResourceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by user on 17.04.2017.
 */
@Component
class EditResourceServiceImpl implements EditResourceService {

    @Autowired
    @Qualifier("HibernateResourceDAO")
    private ResourceDAO resourceDAO;

    @Autowired
    private CreateResourceValidator createResourceValidator;

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

        createResourceValidator.validate(type, title, author, releaseYear);

        Resource resource = resourceOpt.get();
        resource.setResourceType(type);
        resource.setTitle(title);
        resource.setAuthor(author);
        resource.setReleaseYear(releaseYear);
        resourceDAO.update(resource);
    }

    // TODO: 2017.05.23. update this class and validator
}
