package lv.javaguru.java2.services.reservation.validate;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.doThrow;

/**
 * Created by user on 09.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReservationFactoryValidatorImplTest {
    private static final LocalDate DATE_TOO_LATE = LocalDate.now().plusDays(31);
    private static final LocalDate DATE_TODAY = LocalDate.now();
    private static final LocalDate DATE_PLUS_8 = LocalDate.now().plusDays(8);
    private static final LocalDate DATE_PLUS_2 = LocalDate.now().plusDays(2);
    private static final Long ACCOUNT_ID = 1234L;
    private static final Long RESOURCE_ID = 1234L;


    @Mock
    ReservationEndDateValidator reservationEndDateValidator;
    @InjectMocks
    private ReservationFactoryValidator reservationFactoryValidator = new ReservationFactoryValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        doThrow(new IllegalArgumentException("Reservation End Date must be set for no less " +
                "than 7 and no more than 30 days from now!"))
                .when(reservationEndDateValidator).validate(DATE_TOO_LATE);
        doThrow(new IllegalArgumentException("End date cannot be empty."))
                .when(reservationEndDateValidator).validate(null);
    }
    @Test
    public void shouldThrowExceptionWhenStartDateIsNull() {
        thrown.expect(ReservationFactoryException.class);
        thrown.expectMessage("Start date cannot be empty.");
        reservationFactoryValidator.validate(null, DATE_PLUS_8, ACCOUNT_ID, RESOURCE_ID);
    }

    @Test
    public void shouldThrowExceptionWhenEndDateIsNull() {
        thrown.expect(ReservationFactoryException.class);
        thrown.expectMessage("End date cannot be empty.");
        reservationFactoryValidator.validate(DATE_TODAY, null, ACCOUNT_ID, RESOURCE_ID);
    }

    @Test
    public void shouldThrowExceptionWhenReservationFromDateIsWrong() {
        thrown.expect(ReservationFactoryException.class);
        thrown.expectMessage("Reservation Start Date must be set for today!");
        reservationFactoryValidator.validate(DATE_PLUS_2, DATE_PLUS_8, ACCOUNT_ID, RESOURCE_ID);
    }

    @Test
    public void shouldThrowExceptionWhenReservationToDateIsWrong() {
        thrown.expect(ReservationFactoryException.class);
        thrown.expectMessage("Reservation End Date must be set for no less " +
                "than 7 and no more than 30 days from now!");
        reservationFactoryValidator.validate(DATE_TODAY, DATE_TOO_LATE, ACCOUNT_ID, RESOURCE_ID);
    }

    @Test
    public void shouldThrowExceptionWhenResourceIsNull() {
        thrown.expect(ReservationFactoryException.class);
        thrown.expectMessage("Resource ID cannot be empty.");
        reservationFactoryValidator.validate(DATE_TODAY, DATE_PLUS_8, ACCOUNT_ID, null);
    }


    @Test
    public void shouldThrowExceptionWhenAccountIsNull() {
        thrown.expect(ReservationFactoryException.class);
        thrown.expectMessage("Account ID cannot be empty.");
        reservationFactoryValidator.validate(DATE_TODAY, DATE_PLUS_8, null, RESOURCE_ID);
    }

    @Test
    public void shouldThrowExceptionWithCorrectMessageWhenAllIsNull() {
        thrown.expect(ReservationFactoryException.class);
        thrown.expectMessage("Start date cannot be empty.\n" +
                "End date cannot be empty.\n" +
                "Resource ID cannot be empty.\n" +
                "Account ID cannot be empty.");
        reservationFactoryValidator.validate(null, null, null, null);
    }


}