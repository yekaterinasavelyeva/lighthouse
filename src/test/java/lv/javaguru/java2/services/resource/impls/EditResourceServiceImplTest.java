package lv.javaguru.java2.services.resource.impls;

import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.domain.ResourceType;
import lv.javaguru.java2.services.resource.EditResourceService;
import lv.javaguru.java2.services.validators.ResourceEditValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by user on 17.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class EditResourceServiceImplTest {

    private static final ResourceType TYPE = ResourceType.BOOK;
    private static final String TITLE = "title";
    private static final String AUTHOR = "author";
    private static final int RELEASE_YEAR = 2013;
    private static final Long RESOURCE_ID = 1234L;

    @Mock
    private ResourceDAO resourceDAO;
    @Mock
    private ResourceEditValidator validator;

    @InjectMocks
    private EditResourceService service = new EditResourceServiceImpl();

    @Test
    public void checkEditMethodsOrder() {
        Resource resource = Mockito.mock(Resource.class);
        when(resourceDAO.getByID(RESOURCE_ID))
                .thenReturn(Optional.of(resource));
        InOrder inOrder = Mockito.inOrder(resourceDAO, validator, resource);
        service.edit(RESOURCE_ID, TYPE, TITLE, AUTHOR, RELEASE_YEAR);
        inOrder.verify(validator).validate(RESOURCE_ID, TYPE, TITLE, AUTHOR, RELEASE_YEAR);
        inOrder.verify(resourceDAO).getByID(RESOURCE_ID);
        inOrder.verify(resource).setResourceType(TYPE);
        inOrder.verify(resource).setTitle(TITLE);
        inOrder.verify(resource).setAuthor(AUTHOR);
        inOrder.verify(resource).setReleaseYear(RELEASE_YEAR);
        inOrder.verify(resourceDAO).update(resource);
    }
}