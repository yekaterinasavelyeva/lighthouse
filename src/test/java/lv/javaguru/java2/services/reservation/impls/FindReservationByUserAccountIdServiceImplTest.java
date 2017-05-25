package lv.javaguru.java2.services.reservation.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.services.reservation.FindReservationByUserAccountIdService;
import lv.javaguru.java2.services.validators.DataExistValidator;
import lv.javaguru.java2.services.validators.DataInputValidator;
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
public class FindReservationByUserAccountIdServiceImplTest {

    @Mock ReservationDAO reservationDAO;
    @Mock DataExistValidator existValidator;
    @Mock DataInputValidator inputValidator;
    @InjectMocks
    private FindReservationByUserAccountIdService service = new FindReservationByUserAccountIdServiceImpl();

    @Test
    public void checkMethodsOrderInFindByUserAccountIdService() {
        service.find(any(Long.class));
        InOrder inOrder = Mockito.inOrder(reservationDAO, inputValidator, existValidator);
        inOrder.verify(inputValidator).validateUserAccountIdInput(any(Long.class));
        inOrder.verify(existValidator).validateUserAccountIdExists(any(Long.class));
        inOrder.verify(existValidator).validateReservationExistForUserAccountId(any(Long.class));
        inOrder.verify(reservationDAO).getByAccountID(any(Long.class));
    }
}