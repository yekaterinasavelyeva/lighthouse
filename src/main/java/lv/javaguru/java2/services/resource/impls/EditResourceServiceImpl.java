package lv.javaguru.java2.services.resource.impls;

import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.domain.ResourceType;
import lv.javaguru.java2.services.resource.EditResourceService;
import lv.javaguru.java2.services.validators.DataInputValidator;
import lv.javaguru.java2.services.validators.ResourceEditValidator;
import lv.javaguru.java2.services.validators.ResourcePropertyValidator;
import lv.javaguru.java2.services.validators.DataExistValidator;
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
    private ResourceEditValidator validator;

    @Override
    public void edit(Long resourceId, ResourceType type, String title,
                     String author, int releaseYear){
        validator.validate(resourceId, type, title, author, releaseYear);
        Resource resource = getResourceFromDatabase(resourceId);
        resource.setResourceType(type);
        resource.setTitle(title);
        resource.setAuthor(author);
        resource.setReleaseYear(releaseYear);
        resourceDAO.update(resource);
    }

    private Resource getResourceFromDatabase(Long resourceId) {
        Optional<Resource> resourceFromDB = resourceDAO.getByID(resourceId);
        return resourceFromDB.get();
    }
}
