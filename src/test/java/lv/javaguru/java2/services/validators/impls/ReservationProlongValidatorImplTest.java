package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.services.validators.*;
import lv.javaguru.java2.services.exceptions.ReservationProlongException;
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
import static org.mockito.Mockito.doThrow;

/**
 * Created by mobileqa on 28/04/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReservationProlongValidatorImplTest {

    private static final Long EXAMPLE_ID = 1234L;
    private static final LocalDate EXAMPLE_DATE = LocalDate.now().minusDays(1);

    @Mock DataInputValidator dataInputValidator;
    @Mock DataExistValidator dataExistValidator;
    @Mock
    LibraryRuleValidator ruleValidator;

    @InjectMocks
    private ReservationProlongValidator validator = new ReservationProlongValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        doThrow(new IllegalArgumentException("null_id"))
                .when(dataInputValidator).validateReservationIdInput(null);
        doThrow(new IllegalArgumentException("null_date"))
                .when(dataInputValidator).validateEndDateInput(null);
        doThrow(new IllegalArgumentException("example_id"))
                .when(ruleValidator).validateReservationStatusWhenProlongingIt(EXAMPLE_ID);
        doThrow(new IllegalArgumentException("example_date"))
                .when(ruleValidator).validateReservationEndDateLimits(EXAMPLE_DATE);
    }

    @Test
    public void shouldThrowCorrectExceptionWithCollectedMessagesCase1() {
        thrown.expect(ReservationProlongException.class);
        thrown.expectMessage("null_id\nnull_date");
        validator.validate(null, null);
    }

    @Test
    public void shouldThrowCorrectExceptionWithCollectedMessagesCase2() {
        thrown.expect(ReservationProlongException.class);
        thrown.expectMessage("example_id\nnull_date");
        validator.validate(EXAMPLE_ID, null);
    }

    @Test
    public void shouldThrowCorrectExceptionWithCollectedMessagesCase3() {
        thrown.expect(ReservationProlongException.class);
        thrown.expectMessage("null_id\nexample_date");
        validator.validate(null, EXAMPLE_DATE);
    }

    @Test
    public void shouldThrowCorrectExceptionWithCollectedMessagesCase4() {
        thrown.expect(ReservationProlongException.class);
        thrown.expectMessage("example_id\nexample_date");
        validator.validate(EXAMPLE_ID, EXAMPLE_DATE);
    }

    @Test
    public void noExceptionWhenValidationOK() {
        validator.validate(1233L, LocalDate.now());
    }

}