package lv.javaguru.java2.services.reservationServices.validate.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservationServices.validate.ReservationIdValidator;
import lv.javaguru.java2.services.reservationServices.validate.impls.ReservationIdValidatorImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 * Created by user on 17.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReservationIdValidatorImplTest {

    private static final Long NULL_ID = null;
    private static final Long NEGATIVE_ID = -2l;
    private static final Long EXAMPLE_ID = 1234l;

    @Mock
    ReservationDAO reservationDAO;
    @InjectMocks
    private ReservationIdValidator validator = new ReservationIdValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowExceptionWhenReservationIdIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservation ID cannot be null or negative");
        validator.validate(NULL_ID);
    }

    @Test
    public void shouldThrowExceptionWhenReservationIdIsNegative() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservation ID cannot be null or negative");
        validator.validate(NEGATIVE_ID);
    }

    @Test
    public void shouldThrowExceptionWhenReservationIdNotExist() {
        when(reservationDAO.getByID(EXAMPLE_ID))
                .thenReturn(Optional.empty());
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservation not found by id = " + EXAMPLE_ID);
        validator.validate(EXAMPLE_ID);
    }

    @Test
    public void noExceptionWhenReservationIdExists() {
        when(reservationDAO.getByID(EXAMPLE_ID))
                .thenReturn(Optional.of(new Reservation()));
        validator.validate(EXAMPLE_ID);
    }


}