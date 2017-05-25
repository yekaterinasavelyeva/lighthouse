package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.services.exceptions.UserAccountDeleteException;
import lv.javaguru.java2.services.validators.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.doThrow;

/**
 * Created by Viktor on 2017.05.25..
 */
@RunWith(MockitoJUnitRunner.class)
public class UserAccountDeleteValidatorImplTest {

    private static final Long EXAMPLE_ID = 1234L;

    @Mock private DataInputValidator inputValidator;
    @Mock private DataExistValidator existValidator;
    @Mock private LibraryRuleValidator ruleValidator;
    @InjectMocks
    private UserAccountDeleteValidator validator = new UserAccountDeleteValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void checkValidationMethodsOrder() {
        InOrder inOrder = Mockito.inOrder(inputValidator, existValidator, ruleValidator);
        validator.validate(EXAMPLE_ID);
        inOrder.verify(inputValidator).validateUserAccountIdInput(EXAMPLE_ID);
        inOrder.verify(existValidator).validateUserAccountIdExists(EXAMPLE_ID);
        inOrder.verify(ruleValidator).validateReservationStatusWhenDeletingUserAccount(EXAMPLE_ID);
    }

    @Test
    public void shouldThrowCorrectExceptionWhenInputValidationFailed(){
        doThrow(new IllegalArgumentException("smth"))
                .when(inputValidator).validateUserAccountIdInput(EXAMPLE_ID);
        thrown.expect(UserAccountDeleteException.class);
        thrown.expectMessage("smth");
        validator.validate(EXAMPLE_ID);
    }

    @Test
    public void shouldThrowCorrectExceptionWhenExistValidationFailed(){
        doThrow(new IllegalArgumentException("smth"))
                .when(existValidator).validateUserAccountIdExists(EXAMPLE_ID);
        thrown.expect(UserAccountDeleteException.class);
        thrown.expectMessage("smth");
        validator.validate(EXAMPLE_ID);
    }

    @Test
    public void shouldThrowCorrectExceptionTypeWhenRuleValidationFailed(){
        doThrow(new IllegalArgumentException("smth"))
                .when(ruleValidator).validateReservationStatusWhenDeletingUserAccount(EXAMPLE_ID);
        thrown.expect(UserAccountDeleteException.class);
        thrown.expectMessage("smth");
        validator.validate(EXAMPLE_ID);
    }
}