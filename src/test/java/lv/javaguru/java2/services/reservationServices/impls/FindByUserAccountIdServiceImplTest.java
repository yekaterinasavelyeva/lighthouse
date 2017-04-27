package lv.javaguru.java2.services.reservationServices.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.services.reservationServices.FindByUserAccountIdService;
import lv.javaguru.java2.services.reservationServices.impls.FindByUserAccountIdServiceImpl;
import lv.javaguru.java2.services.reservationServices.validate.ReservationUserAccountValidator;
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

    private static final Long EXAMPLE_USERACCOUNT_ID = 1234l;

    @Mock
    ReservationDAO reservationDAO;
    @Mock
    UserAccountIdValidator userAccountIdValidator;
    @Mock
    ReservationUserAccountValidator reservationUserAccountValidator;
    @InjectMocks
    private FindByUserAccountIdService service = new FindByUserAccountIdServiceImpl();

    @Test
    public void checkMethodsOrderInFindByUserAccountIdService() {
        InOrder inOrder = Mockito.inOrder(
                reservationDAO,
                userAccountIdValidator,
                reservationUserAccountValidator);
        service.find(EXAMPLE_USERACCOUNT_ID);
        inOrder.verify(userAccountIdValidator).validate(EXAMPLE_USERACCOUNT_ID);
        inOrder.verify(reservationUserAccountValidator).validate(EXAMPLE_USERACCOUNT_ID);
        inOrder.verify(reservationDAO).getByAccountID(EXAMPLE_USERACCOUNT_ID);
    }
}