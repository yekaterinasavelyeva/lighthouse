package lv.javaguru.java2.services.reservation.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.services.reservation.CloseReservationService;
import lv.javaguru.java2.services.validators.InputValidator;
import lv.javaguru.java2.services.validators.SearchValidator;
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
 * Created by user on 17.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class CloseReservationServiceImplTest {

    private final static Long EXAMPLE_ID = 1234l;

    @Mock
    Reservation reservation;
    @Mock
    ReservationDAO reservationDAO;
    @Mock
    InputValidator inputValidator;
    @Mock
    SearchValidator searchValidator;
    @InjectMocks
    private CloseReservationService service = new CloseReservationServiceImpl();

    @Test
    public void checkMethodsOrderForCloseReservationService() {
        InOrder inOrder = Mockito.inOrder(inputValidator, reservationDAO, reservation, searchValidator);
        when(reservationDAO.getByID(EXAMPLE_ID))
                .thenReturn(Optional.of(reservation));
        service.closeByID(EXAMPLE_ID);
        inOrder.verify(inputValidator).validateReservationIdInput(EXAMPLE_ID);
        inOrder.verify(searchValidator).validateReservationIdExist(EXAMPLE_ID);
        inOrder.verify(reservation).setStatus(ReservationStatus.CLOSED);
        inOrder.verify(reservationDAO).update(reservation);
    }
}