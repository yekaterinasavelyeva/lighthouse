package lv.javaguru.java2.services.resource;

import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.database.jdbc.ResourceDAOImpl;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.domain.ResourceType;
import lv.javaguru.java2.services.resource.validate.ResourceValidator;
import lv.javaguru.java2.services.resource.validate.ResourceValidatorImpl;

import static lv.javaguru.java2.domain.ResourceBuilder.createResource;

/**
 * Created by user on 11.04.2017.
 */
public class ResourceFactoryImpl implements ResourceFactory {

    private ResourceDAO resourceDAO = new ResourceDAOImpl();
    private ResourceValidator resourceValidator = new ResourceValidatorImpl();

    public ResourceFactoryImpl(ResourceValidator validator, ResourceDAO dao){
        resourceValidator = validator;
        resourceDAO = dao;
    }

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
