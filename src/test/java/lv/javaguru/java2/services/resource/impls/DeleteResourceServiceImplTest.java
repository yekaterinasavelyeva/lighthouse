package lv.javaguru.java2.services.resource.impls;

import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.services.resource.DeleteResourceService;
import lv.javaguru.java2.services.validators.ResourceDeleteValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by user on 17.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class DeleteResourceServiceImplTest {

    @Mock private ResourceDAO resourceDAO;
    @Mock private ResourceDeleteValidator validator;
    @Mock private Resource resource;

    @InjectMocks
    private DeleteResourceService service = new DeleteResourceServiceImpl();


    private static final Long resourceId = 1234l;

    @Test
    public void checkResourceDeletionMethodsOrder() {
        InOrder inOrder = Mockito.inOrder(validator, resourceDAO);
        service.delete(resourceId);
        inOrder.verify(validator).validate(resourceId);
        inOrder.verify(resourceDAO).delete(resourceId);
    }

}