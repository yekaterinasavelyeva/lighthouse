package lv.javaguru.java2.services.validate;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.Assert.*;

/**
 * Created by user on 09.04.2017.
 */
public class ReservationValidatorImplTest {

    private ReservationValidator validator = new ReservationValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void shouldThrowExceptionWhenDateIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservation dates must be not empty!");
        validator.validate(null, LocalDate.now().plusDays(8), 12345l);
        validator.validate(LocalDate.now(), null, 12345l);
    }

      @Test
    public void shouldThrowExceptionWhenDateFormatIsWrong() {
        assertFalse(validator.isThisDateValid(""));

    }



}