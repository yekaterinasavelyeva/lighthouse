package lv.javaguru.java2.services.reservation.validate.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.validate.ReservationForUserAccountValidator;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by mobileqa on 27/04/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReservationForUserAccountValidatorImplTest {

    private final static Long EXAMPLE_USERACCOUNT_ID = 1234L;
    private List<Reservation> reservations;

    @Mock
    ReservationDAO reservationDAO;
    @InjectMocks
    private ReservationForUserAccountValidator validator = new ReservationForUserAccountValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        reservations = new ArrayList<>();
        when(reservationDAO.getByAccountID(EXAMPLE_USERACCOUNT_ID))
                .thenReturn(reservations);
    }

    @Test
    public void shouldThrownExceptionWhenNoReservationsOnUserAccount() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservations were not found by account id = " + EXAMPLE_USERACCOUNT_ID);
        validator.validate(EXAMPLE_USERACCOUNT_ID);
    }

    @Test
    public void noExceptionWhenThereAreReservationsForUserAccountId() {
        reservations.add(new Reservation());
        validator.validate(EXAMPLE_USERACCOUNT_ID);
    }
}