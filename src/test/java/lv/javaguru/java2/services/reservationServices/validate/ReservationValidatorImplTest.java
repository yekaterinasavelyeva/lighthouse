package lv.javaguru.java2.services.reservationServices.validate;

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
    public void shouldThrowExceptionWhenStartDateIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Start date cannot be empty.");
        validator.validate(null, LocalDate.now().plusDays(8),
                12345l, 1234l);
    }

    @Test
    public void shouldThrowExceptionWhenEndDateIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("End date cannot be empty.");
        validator.validate(LocalDate.now(), null,
                12345l, 1234l);
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
        thrown.expectMessage("Resource ID cannot be empty.");
        validator.validate(LocalDate.now(), LocalDate.now().plusDays(8),
                12345l, null);
    }


    @Test
    public void shouldThrowExceptionWhenAccountIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Account ID cannot be empty.");
        validator.validate(LocalDate.now(), LocalDate.now().plusDays(8),
                null, 1234l);
    }

    @Test
    public void shouldThrowExceptionWithCorrectMessageWhenAllIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Start date cannot be empty.\n" +
                "End date cannot be empty.\n" +
                "Resource ID cannot be empty.\n" +
                "Account ID cannot be empty.");
        validator.validate(null, null, null, null);
    }


}