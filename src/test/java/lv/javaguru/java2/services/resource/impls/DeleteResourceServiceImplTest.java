package lv.javaguru.java2.services.resource.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.services.resource.DeleteResourceService;
import lv.javaguru.java2.services.resource.impls.DeleteResourceServiceImpl;
import lv.javaguru.java2.services.validators.DeleteResourceValidator;
import lv.javaguru.java2.services.validators.InputValidator;
import lv.javaguru.java2.services.validators.ResourceRuleValidator;
import lv.javaguru.java2.services.validators.SearchValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by user on 17.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class DeleteResourceServiceImplTest {

    @Mock private ResourceDAO resourceDAO;
    @Mock private DeleteResourceValidator validator;
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