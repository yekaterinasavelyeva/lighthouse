package lv.javaguru.java2.services.reservation.validate;

import lv.javaguru.java2.services.reservation.validate.ReservationValidator;
import lv.javaguru.java2.services.reservation.validate.ReservationValidatorImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

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
        validator.validate(null, LocalDate.now().plusDays(8),
                12345l, 1234l);
        validator.validate(LocalDate.now(), null,
                12345l, 1234l);
    }

      @Test
    public void shouldThrowExceptionWhenDateFormatIsWrong() {
             }

    @Test
    public void shouldThrowExceptionWhenReservationFromDateIsWrong() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservation Start Date must be set for today!");
        validator.validate(LocalDate.now().plusDays(2), LocalDate.now().plusDays(8),
                12345l, 1234l);
    }

    @Test
    public void shouldThrowExceptionWhenReservationToDateIsWrong() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservation End Date must be set for no less " +
                "than 7 and no more than 30 days from now!");
        validator.validate(LocalDate.now(), LocalDate.now().plusDays(6),
                12345l, 1234l);
        validator.validate(LocalDate.now(), LocalDate.now().plusDays(31),
                12345l, 1234l);
    }

    @Test
    public void shouldThrowExceptionWhenResourceIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Resource ID must be not empty!");
        validator.validate(LocalDate.now(), LocalDate.now().plusDays(8),
                12345l, null);
    }


    @Test
    public void shouldThrowExceptionWhenAccountIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Account ID must be not empty!");
        validator.validate(LocalDate.now(), LocalDate.now().plusDays(8),
                null, 1234l);
    }



}