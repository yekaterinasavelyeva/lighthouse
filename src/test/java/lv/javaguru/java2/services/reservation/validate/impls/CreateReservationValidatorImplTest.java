package lv.javaguru.java2.services.reservation.validate.impls;

import lv.javaguru.java2.services.reservation.validate.CreateReservationValidator;
import lv.javaguru.java2.services.reservation.validate.InputValidator;
import lv.javaguru.java2.services.reservation.validate.ReservationRuleValidator;
import lv.javaguru.java2.services.reservation.validate.exceptions.ReservationEndDateException;
import lv.javaguru.java2.services.reservation.validate.exceptions.CreateReservationException;
import lv.javaguru.java2.services.reservation.validate.exceptions.ReservationStartDateException;
import lv.javaguru.java2.services.resource.validate.ResourceIdValidator;
import lv.javaguru.java2.services.resource.validate.exceptions.ResourceIdException;
import lv.javaguru.java2.services.useraccount.validate.UserAccountIdValidator;
import lv.javaguru.java2.services.useraccount.validate.exceptions.UserAccountIdException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

/**
 * Created by user on 09.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateReservationValidatorImplTest {

    @Mock
    InputValidator inputValidator;
    @Mock
    ReservationRuleValidator reservationRuleValidator;
    @Mock ResourceIdValidator resourceIdValidator;
    @Mock UserAccountIdValidator userAccountIdValidator;

    @InjectMocks
    private CreateReservationValidator createReservationValidator = new CreateReservationValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        doThrow(new ReservationStartDateException("smth1"))
                .when(inputValidator).validateStartDateInput(null);
        doThrow(new ReservationEndDateException("smth2"))
                .when(inputValidator).validateEndDateInput(null);
        doThrow(new ResourceIdException("smth3"))
                .when(resourceIdValidator).validate(null);
        doThrow(new UserAccountIdException("smth4"))
                .when(userAccountIdValidator).validate(null);
    }

    @Test
    public void shouldThrowCorrectExceptionWithCorrectlyCombinedMessage() {
        thrown.expect(CreateReservationException.class);
        thrown.expectMessage("smth1\nsmth2\nsmth3\nsmth4");
        createReservationValidator.validate(null, null, null, null);
    }


}