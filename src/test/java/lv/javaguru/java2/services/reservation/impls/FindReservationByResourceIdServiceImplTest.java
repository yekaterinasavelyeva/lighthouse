package lv.javaguru.java2.services.reservation.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.services.reservation.FindReservationByResourceIdService;
import lv.javaguru.java2.services.validators.DataInputValidator;
import lv.javaguru.java2.services.validators.DataExistValidator;
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
    DataInputValidator dataInputValidator;
    @Mock ReservationDAO reservationDAO;
    @Mock
    DataExistValidator dataExistValidator;

    @InjectMocks
    private FindReservationByResourceIdService service = new FindReservationByResourceIdServiceImpl();

    @Test
    public void checkMethodsOrderForFindByResouceIdService() {
        service.find(any(Long.class));
        InOrder inOrder = Mockito.inOrder(dataInputValidator, reservationDAO, dataExistValidator);
        inOrder.verify(dataInputValidator).validateResourceIdInput(any(Long.class));
        inOrder.verify(dataExistValidator).validateResourceIdExists(any(Long.class));
        inOrder.verify(dataExistValidator).validateReservationExistForResourceId(any(Long.class));
        inOrder.verify(reservationDAO).getByResourceID(any(Long.class));
    }
}