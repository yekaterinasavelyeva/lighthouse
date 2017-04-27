package lv.javaguru.java2.services.reservation.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.FindByReservationIdService;
import lv.javaguru.java2.services.reservation.validate.ReservationIdValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by user on 18.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class FindByReservationIdServiceImplTest {

    private static final Long EXAMPLE_RESERVATION_ID = 1234l;

    @Mock
    ReservationDAO reservationDAO;
    @Mock
    ReservationIdValidator reservationIdValidator;
    @InjectMocks
    private FindByReservationIdService service = new FindByReservationIdServiceImpl();


    @Test
    public void checkMethodsOrderInFindByReservationIdService() {
        when(reservationDAO.getByID(EXAMPLE_RESERVATION_ID))
                .thenReturn(Optional.of(new Reservation()));
        InOrder inOrder = Mockito.inOrder(reservationIdValidator, reservationDAO);
        service.find(EXAMPLE_RESERVATION_ID);
        inOrder.verify(reservationIdValidator).validate(EXAMPLE_RESERVATION_ID);
        inOrder.verify(reservationDAO).getByID(EXAMPLE_RESERVATION_ID);
    }

}