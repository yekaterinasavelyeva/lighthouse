package lv.javaguru.java2.services.reservation.validate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by user on 23.04.2017.
 */
public class ReservationDateToValidatorImplTest {
    private ReservationDateToValidator validator = new ReservationDateToValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowExceptionWhenReservationDateIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservation date must be not empty!");
        validator.validate(null);
    }

    @Test
    public void shouldThrowExceptionWhenReservationDateIsWrong() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservation End Date must be set for no less " +
                "than 7 and no more than 30 days from now!");
        validator.validate(LocalDate.now());
    }
}