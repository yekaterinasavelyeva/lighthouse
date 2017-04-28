package lv.javaguru.java2.services.reservation.validate.impls;

import lv.javaguru.java2.services.reservation.validate.InputValidator;
import lv.javaguru.java2.services.reservation.validate.ProlongReservationValidator;
import lv.javaguru.java2.services.reservation.validate.ReservationRuleValidator;
import lv.javaguru.java2.services.reservation.validate.SearchValidator;
import lv.javaguru.java2.services.reservation.validate.exceptions.ProlongReservationException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

/**
 * Created by mobileqa on 28/04/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProlongReservationValidatorImplTest {

    @Mock
    InputValidator inputValidator;
    @Mock
    SearchValidator searchValidator;
    @Mock
    ReservationRuleValidator ruleValidator;

    @InjectMocks
    private ProlongReservationValidator validator = new ProlongReservationValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        doThrow(new IllegalArgumentException("smth1"))
                .when(inputValidator).validateReservationIdInput(null);
        doThrow(new IllegalArgumentException("smth1"))
                .when(searchValidator).validateReservationIdExist(any(Long.class));
        doThrow(new IllegalArgumentException("smth2"))
                .when(inputValidator).validateEndDateInput(null);
        doThrow(new IllegalArgumentException("smth2"))
                .when(inputValidator).validateEndDateInput(any(LocalDate.class));
        thrown.expect(ProlongReservationException.class);
        thrown.expectMessage("smth1\nsmth2");
    }
    @Test
    public void shouldThrowExceptionWithCorrectMessageForCombination1() {
        validator.validate(null, null);
    }

    @Test
    public void shouldThrowExceptionWithCorrectMessageForCombination2() {
        validator.validate(1234l, null);
    }

    @Test
    public void shouldThrowExceptionWithCorrectMessageForCombination3() {
        validator.validate(null, LocalDate.now());
    }

    @Test
    public void shouldThrowExceptionWithCorrectMessageForCombination4() {
        validator.validate(1234l, LocalDate.now());
    }

}