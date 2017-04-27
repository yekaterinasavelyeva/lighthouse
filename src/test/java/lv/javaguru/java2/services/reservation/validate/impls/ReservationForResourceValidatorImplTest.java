package lv.javaguru.java2.services.reservation.validate.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.validate.ReservationForResourceValidator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by mobileqa on 27/04/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReservationForResourceValidatorImplTest {

    private final static Long EXAMPLE_RESOURCE_ID = 1234L;
    private List<Reservation> reservations;

    @Mock
    ReservationDAO reservationDAO;
    @InjectMocks
    private ReservationForResourceValidator validator = new ReservationForResourceValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        reservations = new ArrayList<>();
        when(reservationDAO.getByResourceID(EXAMPLE_RESOURCE_ID))
                .thenReturn(reservations);
    }

    @Test
    public void shouldThrowExceptionIfNoReservationsForResourseId() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservations were not found for resource id = " + EXAMPLE_RESOURCE_ID);
        validator.validate(EXAMPLE_RESOURCE_ID);
    }

    @Test
    public void noExceptionWhenThereAreReservationsForResourceId() {
        reservations.add(new Reservation());
        validator.validate(EXAMPLE_RESOURCE_ID);
    }

}