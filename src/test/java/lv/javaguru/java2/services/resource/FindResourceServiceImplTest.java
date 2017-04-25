package lv.javaguru.java2.services.resource;

import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.domain.ResourceType;
import lv.javaguru.java2.services.resource.validate.ResourceIdValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by user on 17.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class FindResourceServiceImplTest {

    private FindResourceService service;
    private ResourceDAO resourceDAO;
    private ResourceIdValidator validator;
    private Resource resource = new Resource();

    private static final Long resourceId = 1234l;

    @Before
    public void init() {
        validator = mock(ResourceIdValidator.class);
        resourceDAO = mock(ResourceDAO.class);
        service = new FindResourceServiceImpl(validator, resourceDAO);
        resource.setResourceID(resourceId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIResourceNotFoundTest() {
        service.findResource(resourceId);
    }

    @Test
    public void checkResourceSearchMethodsOrder() {
        when(resourceDAO.getByID(resourceId)).thenReturn(Optional.of(resource));
        service.findResource(resourceId);
        InOrder inOrder = Mockito.inOrder(validator, resourceDAO);
        inOrder.verify(validator).validate(resourceId);
        inOrder.verify(resourceDAO).getByID(resourceId);
    }

    @Test
    public void getResourceByIDTest(){
        resource.setAuthor("Author");
        resource.setTitle("Title");
        resource.setReleaseYear(2016);
        resource.setResourceType(ResourceType.ARTICLE);

        when(resourceDAO.getByID(any(Long.class))).thenReturn(Optional.of(resource));
        Resource resourceFromDB = service.findResource(resourceId);

        assertEquals(resource.getAuthor(), resourceFromDB.getAuthor());
        assertEquals(resource.getTitle(), resourceFromDB.getTitle());
        assertEquals(resource.getReleaseYear(), resourceFromDB.getReleaseYear());
        assertEquals(resource.getResourceType(), resourceFromDB.getResourceType());
    }


}