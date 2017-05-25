package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.services.validators.*;
import lv.javaguru.java2.services.exceptions.ReservationPropertyException;
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
public class ReservationPropertyValidatorImplTest {

    @Mock DataInputValidator dataInputValidator;
    @Mock DataExistValidator dataExistValidator;
    @Mock LibraryRuleValidator ruleValidator;

    @InjectMocks
    private ReservationPropertyValidator validator = new ReservationPropertyValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        doThrow(new IllegalArgumentException("smth1"))
                .when(dataInputValidator).validateStartDateInput(null);
        doThrow(new IllegalArgumentException("smth1"))
                .when(ruleValidator).validateReservationStartDateLimits(any(LocalDate.class));
        doThrow(new IllegalArgumentException("smth2"))
                .when(dataInputValidator).validateEndDateInput(null);
        doThrow(new IllegalArgumentException("smth2"))
                .when(ruleValidator).validateReservationEndDateLimits(any(LocalDate.class));
        doThrow(new IllegalArgumentException("smth3"))
                .when(dataInputValidator).validateResourceIdInput(null);
        doThrow(new IllegalArgumentException("smth3"))
                .when(ruleValidator).validateResourceReservationStatusWhenCreatingReservation(any(Long.class));
        doThrow(new IllegalArgumentException("smth4"))
                .when(dataInputValidator).validateUserAccountIdInput(null);
        thrown.expect(ReservationPropertyException.class);
        thrown.expectMessage("smth1\nsmth2\nsmth3\nsmth4");
    }

    @Test
    public void shouldThrowExceptionWithCorrectMessageForCombination1() {
        validator.validate(null, null, null, null);
    }

    @Test
    public void shouldThrowExceptionWithCorrectMessageForCombination2() {
        validator.validate(LocalDate.now(), null, null, null);
    }

    @Test
    public void shouldThrowExceptionWithCorrectMessageForCombination3() {
        validator.validate(null, LocalDate.now(), null, null);
    }

    @Test
    public void shouldThrowExceptionWithCorrectMessageForCombination4() {
        validator.validate(null, LocalDate.now(), null, null);
    }

    @Test
    public void shouldThrowExceptionWithCorrectMessageForCombination5() {
        validator.validate(null, null, 1234l, null);
    }

    @Test
    public void shouldThrowExceptionWithCorrectMessageForCombination6() {
        validator.validate(LocalDate.now(), LocalDate.now(), 1234l, null);
    }


}