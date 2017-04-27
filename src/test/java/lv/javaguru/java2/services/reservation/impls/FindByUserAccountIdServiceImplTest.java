package lv.javaguru.java2.services.reservation.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.services.reservation.FindByUserAccountIdService;
import lv.javaguru.java2.services.reservation.validate.ReservationForUserAccountValidator;
import lv.javaguru.java2.services.useraccount.validate.UserAccountIdValidator;
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
public class FindByUserAccountIdServiceImplTest {

    @Mock
    ReservationDAO reservationDAO;
    @Mock
    UserAccountIdValidator userAccountIdValidator;
    @Mock
    ReservationForUserAccountValidator reservationForUserAccountValidator;
    @InjectMocks
    private FindByUserAccountIdService service = new FindByUserAccountIdServiceImpl();

    @Test
    public void checkMethodsOrderInFindByUserAccountIdService() {
        service.find(any(Long.class));
        InOrder inOrder = Mockito.inOrder(reservationDAO, userAccountIdValidator, reservationForUserAccountValidator);
        inOrder.verify(userAccountIdValidator).validate(any(Long.class));
        inOrder.verify(reservationForUserAccountValidator).validate(any(Long.class));
        inOrder.verify(reservationDAO).getByAccountID(any(Long.class));
    }
}