package lv.javaguru.java2.services.reservation.validate.impls;

import lv.javaguru.java2.services.reservation.validate.ReservationStartDateValidator;
import lv.javaguru.java2.services.reservation.validate.exceptions.ReservationStartDateException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by mobileqa on 27/04/17.
 */
public class ReservationStartDateValidatorImplTest {

    private static final LocalDate DATE_TODAY = LocalDate.now();
    private static final LocalDate DATE_YESTERDAY = LocalDate.now().minusDays(1);
    private static final LocalDate DATE_TOMORROW = LocalDate.now().plusDays(1);

    private ReservationStartDateValidator validator = new ReservationStartDateValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowExceptionWhenReservationStartDateIsNull() {
        thrown.expect(ReservationStartDateException.class);
        thrown.expectMessage("Start date cannot be empty.");
        validator.validate(null);
    }

    @Test
    public void shouldThrowExceptionWhenReservationStartDateIsBeforeToday() {
        thrown.expect(ReservationStartDateException.class);
        thrown.expectMessage("Reservation Start Date must be set for today!");
        validator.validate(DATE_YESTERDAY);
    }

    @Test
    public void shouldThrowExceptionWhenReservationStartDateIsBeforeYesterday() {
        thrown.expect(ReservationStartDateException.class);
        thrown.expectMessage("Reservation Start Date must be set for today!");
        validator.validate(DATE_TOMORROW);
    }

    @Test
    public void noExceptionWhenReservationStartDateIsToday() {
        validator.validate(DATE_TODAY);
    }

}