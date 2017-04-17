package lv.javaguru.java2.services.resource;

import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.domain.ResourceType;
import lv.javaguru.java2.services.resource.validate.ResourceIdValidator;
import lv.javaguru.java2.services.resource.validate.ResourceValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by user on 17.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class EditResourceServiceImplTest {

    private EditResourceService service;
    private ResourceDAO resourceDAO;
    private ResourceValidator validator;
    private ResourceIdValidator idValidator;
    private Resource resource = new Resource();

    private static final ResourceType TYPE = ResourceType.BOOK;
    private static final String TITLE = "title";
    private static final String AUTHOR = "author";
    private static final int RELEASEYEAR = 2013;
    private static final Long RESOURCEID = 1234l;

    @Before
    public void init() {
        validator = mock(ResourceValidator.class);
        idValidator = mock(ResourceIdValidator.class);
        resourceDAO = mock(ResourceDAO.class);
        service = new EditResourceServiceImpl(idValidator, validator, resourceDAO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfResourceNotFoundTest() {
        service.edit(RESOURCEID, TYPE, TITLE, AUTHOR, RELEASEYEAR);
    }

    @Test
    public void shouldBePossibilityToProvideResourceDetailsForEdit() {
        service = mock(EditResourceService.class);
        service.edit(RESOURCEID, TYPE, TITLE, AUTHOR, RELEASEYEAR);
        verify(service).edit(RESOURCEID, TYPE, TITLE, AUTHOR, RELEASEYEAR);
    }

    @Test
    public void checkResourceEditMethodsOrder() {
        when(resourceDAO.getByID(RESOURCEID)).thenReturn(Optional.of(resource));
        service.edit(RESOURCEID, TYPE, TITLE, AUTHOR, RELEASEYEAR);
        InOrder inOrder = Mockito.inOrder(idValidator, validator, resourceDAO);
        inOrder.verify(idValidator).validate(RESOURCEID);
        inOrder.verify(validator).validate(TYPE, TITLE, AUTHOR, RELEASEYEAR);
        inOrder.verify(resourceDAO).update(any(Resource.class));
    }

    @Test
    public void editResourceTest() {
        resource.setResourceID(RESOURCEID);
        resource.setTitle(TITLE);
        resource.setAuthor(AUTHOR);
        resource.setResourceType(TYPE);
        resource.setReleaseYear(RELEASEYEAR);
        when(resourceDAO.getByID(any(Long.class))).thenReturn(Optional.of(resource));
        service.edit(resource.getResourceID(), ResourceType.ARTICLE, "new Title",
                "new Author", 2014);
        Optional <Resource> changedResource = resourceDAO.getByID(resource.getResourceID());
        assertTrue(changedResource.isPresent());
        assertEquals(resource.getResourceType(), changedResource.get().getResourceType());
        assertEquals(resource.getReleaseYear(), changedResource.get().getReleaseYear());
        assertEquals(resource.getTitle(), changedResource.get().getTitle());
        assertEquals(resource.getAuthor(), changedResource.get().getAuthor());
    }

}