package lv.javaguru.java2.services.reservation;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.services.reservation.reservationServiceImpls.CloseReservationServiceImpl;
import lv.javaguru.java2.services.reservation.validate.ReservationIdValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
    ReservationIdValidator validator;
    @InjectMocks
    private CloseReservationService service = new CloseReservationServiceImpl();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Test
    public void checkMethodsOrderForCloseReservationService() {
        InOrder inOrder = prepareMocksInOrder();
        service.closeByID(EXAMPLE_ID);
        inOrder.verify(validator).validate(EXAMPLE_ID);
        inOrder.verify(reservation).setStatus(ReservationStatus.CLOSED);
        inOrder.verify(reservationDAO).update(reservation);
    }

    private InOrder prepareMocksInOrder() {
        when(reservationDAO.getByID(EXAMPLE_ID))
                .thenReturn(Optional.of(reservation));
        return Mockito.inOrder(validator, reservationDAO, reservation);
    }
}