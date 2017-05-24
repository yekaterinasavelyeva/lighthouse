package lv.javaguru.java2.services.resource.impls;

import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.domain.ResourceType;
import lv.javaguru.java2.services.resource.CreateResourceService;
import lv.javaguru.java2.services.validators.ResourcePropertyValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by user on 11.04.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class CreateResourceServiceImplTest {

    @InjectMocks
    private CreateResourceService service = new CreateResourceServiceImpl();
    @Mock
    private ResourceDAO resourceDAO;
    @Mock
    private ResourcePropertyValidator validator;

    private static final String TITLE = "Game of Thrones";
    private static final String AUTHOR = "George R. Martin";
    private static final int releaseYear = 2012;

    @Test
    public void shouldBePossibilityToProvideResourceDetails() {
        service = mock(CreateResourceService.class);
        service.create(ResourceType.BOOK, TITLE, AUTHOR, releaseYear);
        verify(service).create(ResourceType.BOOK, TITLE, AUTHOR, releaseYear);
    }

    @Test
    public void checkResourceCreationMethodsOrder() {
        service.create(ResourceType.BOOK, TITLE, AUTHOR, releaseYear);
        InOrder inOrder = Mockito.inOrder(validator, resourceDAO);
        inOrder.verify(validator).validate(ResourceType.BOOK, TITLE, AUTHOR, releaseYear);
        inOrder.verify(resourceDAO).save(any(Resource.class));
    }

    @Test
    public void createResource() {
        Resource resource = new Resource();
        resource.setAuthor(AUTHOR);
        resource.setTitle(TITLE);
        resource.setReleaseYear(releaseYear);
        resource.setResourceType(ResourceType.BOOK);
        resource.setResourceID(1234l);
        when(resourceDAO.save(any(Resource.class))).thenReturn(resource);
        when(resourceDAO.getByID(any(Long.class))).thenReturn(Optional.of(resource));
        Resource newResource = service.create(ResourceType.BOOK,TITLE, AUTHOR, releaseYear);

        Optional <Resource> resourceFromDB = resourceDAO.getByID(1234l);
        assertEquals(newResource.getResourceID(), resourceFromDB.get().getResourceID());
        assertEquals(newResource.getResourceType(), resourceFromDB.get().getResourceType());
        assertEquals(newResource.getTitle(), resourceFromDB.get().getTitle());
        assertEquals(newResource.getAuthor(), resourceFromDB.get().getAuthor());
        assertEquals(newResource.getReleaseYear(), resourceFromDB.get().getReleaseYear());
    }

}