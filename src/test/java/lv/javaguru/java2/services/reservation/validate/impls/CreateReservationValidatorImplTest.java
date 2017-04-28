package lv.javaguru.java2.services.reservation.validate.impls;

import lv.javaguru.java2.services.reservation.validate.CreateReservationValidator;
import lv.javaguru.java2.services.reservation.validate.InputValidator;
import lv.javaguru.java2.services.reservation.validate.ReservationRuleValidator;
import lv.javaguru.java2.services.reservation.validate.exceptions.CreateReservationException;
import lv.javaguru.java2.services.resource.validate.ResourceIdValidator;
import lv.javaguru.java2.services.useraccount.validate.UserAccountIdValidator;
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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

/**
 * Created by user on 09.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateReservationValidatorImplTest {

    @Mock InputValidator inputValidator;
    @Mock ReservationRuleValidator ruleValidator;
    @Mock ResourceIdValidator resourceIdValidator;
    @Mock UserAccountIdValidator userAccountIdValidator;

    @InjectMocks
    private CreateReservationValidator createReservationValidator = new CreateReservationValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        doThrow(new IllegalArgumentException("smth1"))
                .when(inputValidator).validateStartDateInput(null);
        doThrow(new IllegalArgumentException("smth1"))
                .when(ruleValidator).validateStartDateForNewReservation(any(LocalDate.class));
        doThrow(new IllegalArgumentException("smth2"))
                .when(inputValidator).validateEndDateInput(null);
        doThrow(new IllegalArgumentException("smth2"))
                .when(ruleValidator).validateEndDateForNewReservation(any(LocalDate.class));
        doThrow(new IllegalArgumentException("smth3"))
                .when(resourceIdValidator).validate(null);
        doThrow(new IllegalArgumentException("smth3"))
                .when(ruleValidator).validateResourceIdForNewReservation(any(Long.class));
        doThrow(new IllegalArgumentException("smth4"))
                .when(userAccountIdValidator).validate(null);
    }

    @Test
    public void shouldThrowExceptionWithCorrectMessageForCombination1() {
        thrown.expect(CreateReservationException.class);
        thrown.expectMessage("smth1\nsmth2\nsmth3\nsmth4");
        createReservationValidator.validate(null, null, null, null);
    }

    @Test
    public void shouldThrowExceptionWithCorrectMessageForCombination2() {
        thrown.expect(CreateReservationException.class);
        thrown.expectMessage("smth1\nsmth2\nsmth3\nsmth4");
        createReservationValidator.validate(LocalDate.now(), null, null, null);
    }

    @Test
    public void shouldThrowExceptionWithCorrectMessageForCombination3() {
        thrown.expect(CreateReservationException.class);
        thrown.expectMessage("smth1\nsmth2\nsmth3\nsmth4");
        createReservationValidator.validate(null, LocalDate.now(), null, null);
    }

    @Test
    public void shouldThrowExceptionWithCorrectMessageForCombination4() {
        thrown.expect(CreateReservationException.class);
        thrown.expectMessage("smth1\nsmth2\nsmth3\nsmth4");
        createReservationValidator.validate(null, LocalDate.now(), null, null);
    }

    @Test
    public void shouldThrowExceptionWithCorrectMessageForCombination5() {
        thrown.expect(CreateReservationException.class);
        thrown.expectMessage("smth1\nsmth2\nsmth3\nsmth4");
        createReservationValidator.validate(null, null, 1234l, null);
    }

    @Test
    public void shouldThrowExceptionWithCorrectMessageForCombination6() {
        thrown.expect(CreateReservationException.class);
        thrown.expectMessage("smth1\nsmth2\nsmth3\nsmth4");
        createReservationValidator.validate(LocalDate.now(), LocalDate.now(), 1234l, null);
    }


}