package lv.javaguru.java2.services.reservation.validate.impls;

import lv.javaguru.java2.services.reservation.validate.ReservationEndDateValidator;
import lv.javaguru.java2.services.reservation.validate.exceptions.ReservationEndDateException;
import lv.javaguru.java2.services.reservation.validate.impls.ReservationEndDateValidatorImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

/**
 * Created by user on 23.04.2017.
 */
public class ReservationEndDateValidatorImplTest {

    private static final LocalDate DATE_TOO_EARLY = LocalDate.now().plusDays(6);
    private static final LocalDate DATE_MIN_POSSIBLE = LocalDate.now().plusDays(7);
    private static final LocalDate DATE_TOO_LATE = LocalDate.now().plusDays(31);
    private static final LocalDate DATE_MAX_POSSIBLE = LocalDate.now().plusDays(30);


    private ReservationEndDateValidator validator = new ReservationEndDateValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowExceptionWhenReservationEndDateIsNull() {
        thrown.expect(ReservationEndDateException.class);
        thrown.expectMessage("End date cannot be empty.");
        validator.validate(null);
    }

    @Test
    public void shouldThrowExceptionWhenReservationEndDateIsTooEarly() {
        thrown.expect(ReservationEndDateException.class);
        thrown.expectMessage("Reservation End Date must be set for no less " +
                "than 7 and no more than 30 days from now!");
        validator.validate(DATE_TOO_EARLY);
    }

    @Test
    public void shouldThrowExceptionWhenReservationEndDateIsTooLate() {
        thrown.expect(ReservationEndDateException.class);
        thrown.expectMessage("Reservation End Date must be set for no less " +
                "than 7 and no more than 30 days from now!");
        validator.validate(DATE_TOO_LATE);
    }

    @Test
    public void noExceptionWhenReservationEndDateIsMinPossible() {
        validator.validate(DATE_MIN_POSSIBLE);
    }

    @Test
    public void noExceptionWhenReservationEndDateIsMaxPossible() {
        validator.validate(DATE_MAX_POSSIBLE);
    }

}