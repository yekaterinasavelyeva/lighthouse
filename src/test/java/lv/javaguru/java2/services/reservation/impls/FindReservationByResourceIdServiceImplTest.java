package lv.javaguru.java2.services.reservation.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.services.reservation.FindReservationByResourceIdService;
import lv.javaguru.java2.services.reservation.validate.SearchValidator;
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
public class FindReservationByResourceIdServiceImplTest {

    @Mock
    ResourceIdValidator resourceIdValidator;
    @Mock
    ReservationDAO reservationDAO;
    @Mock
    SearchValidator searchValidator;
    @InjectMocks
    private FindReservationByResourceIdService service = new FindReservationByResourceIdServiceImpl();

    @Test
    public void checkMethodsOrderForFindByResouceIdService() {
        service.find(any(Long.class));
        InOrder inOrder = Mockito.inOrder(resourceIdValidator, reservationDAO, searchValidator);
        inOrder.verify(resourceIdValidator).validate(any(Long.class));
        inOrder.verify(searchValidator).validateReservationExistForResourceId(any(Long.class));
        inOrder.verify(reservationDAO).getByResourceID(any(Long.class));
    }
}