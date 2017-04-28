package lv.javaguru.java2.services.reservation.validate.impls;

import lv.javaguru.java2.services.reservation.validate.InputValidator;
import lv.javaguru.java2.services.reservation.validate.exceptions.ReservationEndDateException;
import lv.javaguru.java2.services.reservation.validate.exceptions.ReservationStartDateException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by mobileqa on 28/04/17.
 */
public class InputValidatorImplTest {

    private static final Long NEGATIVE_ID = -2l;

    private InputValidator validator = new InputValidatorImpl();
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowExceptionWhenReservationStartDateIsNull() {
        thrown.expect(ReservationStartDateException.class);
        thrown.expectMessage("Start date cannot be empty.");
        validator.validateStartDateInput(null);
    }

    @Test
    public void shouldThrowExceptionWhenReservationEndDateIsNull() {
        thrown.expect(ReservationEndDateException.class);
        thrown.expectMessage("End date cannot be empty.");
        validator.validateEndDateInput(null);
    }

    @Test
    public void shouldThrowExceptionWhenReservationIdIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservation ID cannot be null or negative");
        validator.validateReservationIdInput(null);
    }

    @Test
    public void shouldThrowExceptionWhenReservationIdIsNegative() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservation ID cannot be null or negative");
        validator.validateReservationIdInput(NEGATIVE_ID);
    }

}