package lv.javaguru.java2.services.reservation.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.ProlongReservationService;
import lv.javaguru.java2.services.validators.ReservationProlongValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 * Created by mobileqa on 28/04/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProlongReservationServiceImplTest {

    private final static LocalDate EXAMPLE_DATE = LocalDate.now();
    private final static Long EXAMPLE_ID = 1234l;

    @Mock
    Reservation reservation;
    @Mock
    ReservationDAO reservationDAO;
    @Mock
    ReservationProlongValidator validator;
    @InjectMocks
    public ProlongReservationService service = new ProlongReservationServiceImpl();

    @Test
    public void checkMethodsOrderForProlongReservationService() {
        InOrder inOrder = Mockito.inOrder(reservationDAO, validator, reservation);
        when(reservationDAO.getByID(EXAMPLE_ID))
                .thenReturn(Optional.of(reservation));
        service.prolongReservationToDate(EXAMPLE_ID, EXAMPLE_DATE);
        inOrder.verify(validator).validate(EXAMPLE_ID, EXAMPLE_DATE);
        inOrder.verify(reservation).setDateTo(EXAMPLE_DATE);
        inOrder.verify(reservationDAO).update(reservation);
    }
}