package lv.javaguru.java2.services.resource.impls;

import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.services.resource.FindResourceService;
import lv.javaguru.java2.services.validators.DataExistValidator;
import lv.javaguru.java2.services.validators.DataInputValidator;
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
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by user on 17.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class FindResourceServiceImplTest {

    private static final Long EXAMPLE_ID = 1234L;

    @Mock private ResourceDAO resourceDAO;
    @Mock private DataInputValidator inputValidator;
    @Mock private DataExistValidator existValidator;
    @Mock private Resource resource;
    @InjectMocks
    private FindResourceService service = new FindResourceServiceImpl();

    @Test
    public void checkFindMethodsOrderAndReturnResult() {
        when(resourceDAO.getByID(EXAMPLE_ID)).thenReturn(Optional.of(resource));
        Resource fromService = service.findResource(EXAMPLE_ID);
        InOrder inOrder = Mockito.inOrder(resourceDAO, inputValidator, existValidator);
        inOrder.verify(inputValidator).validateResourceIdInput(EXAMPLE_ID);
        inOrder.verify(existValidator).validateResourceIdExists(EXAMPLE_ID);
        inOrder.verify(resourceDAO).getByID(EXAMPLE_ID);
        assertEquals(fromService, resource);

    }



}