package lv.javaguru.java2.services.reservation.validate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by user on 17.04.2017.
 */
public class ReservationIdValidatorImplTest {

    private ReservationIdValidator validator = new ReservationIdValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowExceptionWhenReservationIDIsNullOrLessThanZero() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservation ID must not be empty or less than 0");
        validator.validate(null);
        validator.validate(-2l);
    }

}