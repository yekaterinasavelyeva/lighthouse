package lv.javaguru.java2.services.resource.impls;

import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.domain.ResourceType;
import lv.javaguru.java2.services.resource.CreateResourceService;
import lv.javaguru.java2.services.validators.ResourcePropertyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static lv.javaguru.java2.domain.ResourceBuilder.createResource;

/**
 * Created by user on 11.04.2017.
 */
@Component
class CreateResourceServiceImpl implements CreateResourceService {

    @Autowired
    @Qualifier("HibernateResourceDAO")
    private ResourceDAO resourceDAO;
    @Autowired
    private ResourcePropertyValidator validator;

    @Override
    public Resource create(ResourceType type, String title, String author, int releaseYear){
        validator.validate(type, title, author, releaseYear);
        Resource resource = buildWithParameters(type, title, author, releaseYear);
        return resourceDAO.save(resource);
    }

    private Resource buildWithParameters(ResourceType type, String title, String author, int releaseYear) {
        return createResource()
                    .withResourceType(type)
                    .withTitle(title)
                    .withAuthor(author)
                    .withReleaseYear(releaseYear)
                    .build();
    }
}
