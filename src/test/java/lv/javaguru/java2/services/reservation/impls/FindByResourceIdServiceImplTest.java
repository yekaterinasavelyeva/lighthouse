package lv.javaguru.java2.services.reservation.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.services.reservation.FindByResourceIdService;
import lv.javaguru.java2.services.reservation.impls.FindByResourceIdServiceImpl;
import lv.javaguru.java2.services.reservation.validate.ReservationForResourceValidator;
import lv.javaguru.java2.services.resource.validate.ResourceIdValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by user on 23.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class FindByResourceIdServiceImplTest {

    @Mock
    ResourceIdValidator resourceIdValidator;
    @Mock
    ReservationDAO reservationDAO;
    @Mock
    ReservationForResourceValidator reservationForResourceValidator;
    @InjectMocks
    private FindByResourceIdService service = new FindByResourceIdServiceImpl();

    @Test
    public void checkMethodsOrderForFindByResouceIdService() {
        service.find(any(Long.class));
        InOrder inOrder = Mockito.inOrder(resourceIdValidator, reservationDAO, reservationForResourceValidator);
        inOrder.verify(resourceIdValidator).validate(any(Long.class));
        inOrder.verify(reservationForResourceValidator).validate(any(Long.class));
        inOrder.verify(reservationDAO).getByResourceID(any(Long.class));
    }
}