package lv.javaguru.java2.services.reservationServices.validate.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservationServices.validate.ReservationUserAccountValidator;
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
public class ReservationUserAccountValidatorImplTest {

    private final static Long EXAMPLE_USERACCOUNT_ID = 1234L;

    @Mock
    ReservationDAO reservationDAO;
    @InjectMocks
    private ReservationUserAccountValidator validator = new ReservationUserAccountValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrownExceptionWhenNoReservationsOnUserAccount() {
        List<Reservation> emptyList = prepareEmptyList();
        when(reservationDAO.getByAccountID(EXAMPLE_USERACCOUNT_ID))
                .thenReturn(emptyList);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservations were not found by account id = " + EXAMPLE_USERACCOUNT_ID);
        validator.validate(EXAMPLE_USERACCOUNT_ID);
    }

    @Test
    public void noExceptionWhenReservationsAreOnUserAccount() {
        List<Reservation> notEmptyList = prepareNotEmptyList();
        when(reservationDAO.getByAccountID(EXAMPLE_USERACCOUNT_ID))
                .thenReturn(notEmptyList);
        validator.validate(EXAMPLE_USERACCOUNT_ID);
    }

    private List<Reservation> prepareNotEmptyList() {
        List<Reservation> reservations = prepareEmptyList();
        reservations.add(new Reservation());
        return reservations;
    }

    private List<Reservation> prepareEmptyList() {
        return new ArrayList<>();
    }
}