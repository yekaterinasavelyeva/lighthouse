package lv.javaguru.java2.services.resource;

import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.database.jdbc.ResourceDAOImpl;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.domain.ResourceType;
import lv.javaguru.java2.services.resource.validate.ResourceValidator;
import lv.javaguru.java2.services.resource.validate.impls.ResourceValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static lv.javaguru.java2.domain.ResourceBuilder.createResource;

/**
 * Created by user on 11.04.2017.
 */
@Component
public class CreateResourceServiceImpl implements CreateResourceService {

    @Autowired
    @Qualifier("HibernateResourceDAO")
    private ResourceDAO resourceDAO;
    @Autowired
    private ResourceValidator resourceValidator;

    @Override
    public Resource create(ResourceType type, String title, String author, int releaseYear){
        resourceValidator.validate(type, title, author, releaseYear);
        Resource resource = createResource()
                .withResourceType(type)
                .withTitle(title)
                .withAuthor(author)
                .withReleaseYear(releaseYear)
                .build();

        return resourceDAO.save(resource);
    };
}
