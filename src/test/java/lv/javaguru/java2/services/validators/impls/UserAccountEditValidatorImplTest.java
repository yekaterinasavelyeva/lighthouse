package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.domain.UserAccountState;
import lv.javaguru.java2.services.exceptions.UserAccountEditException;
import lv.javaguru.java2.services.exceptions.UserAccountPropertyException;
import lv.javaguru.java2.services.validators.DataExistValidator;
import lv.javaguru.java2.services.validators.DataInputValidator;
import lv.javaguru.java2.services.validators.UserAccountEditValidator;
import lv.javaguru.java2.services.validators.UserAccountPropertyValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;

/**
 * Created by Viktor on 2017.05.25..
 */
@RunWith(MockitoJUnitRunner.class)
public class UserAccountEditValidatorImplTest {

    private static final Long EXAMPLE_LONG = 1234L;
    private static final String EXAMPLE_STRING = "example";
    private static final UserAccountState EXAMPLE_STATE = UserAccountState.VISITOR;

    @Mock DataInputValidator inputValidator;
    @Mock DataExistValidator existValidator;
    @Mock UserAccountPropertyValidator propertyValidator;
    @InjectMocks
    private UserAccountEditValidator validator = new UserAccountEditValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void checkValidationMethodsOrder() {
        InOrder inOrder = Mockito.inOrder(inputValidator, existValidator, propertyValidator);
        validator.validate(EXAMPLE_LONG, EXAMPLE_STRING, EXAMPLE_STRING, EXAMPLE_STATE);
        inOrder.verify(inputValidator).validateUserAccountIdInput(EXAMPLE_LONG);
        inOrder.verify(existValidator).validateUserAccountIdExists(EXAMPLE_LONG);
        inOrder.verify(propertyValidator).validate(EXAMPLE_STRING, EXAMPLE_STRING, EXAMPLE_STATE);
    }

    @Test
    public void shouldThrowCorrectExceptionWhenInputValidationFail() {
        doThrow(new IllegalArgumentException("inputValidationFail"))
                .when(inputValidator).validateUserAccountIdInput(EXAMPLE_LONG);
        thrown.expect(UserAccountEditException.class);
        thrown.expectMessage("inputValidationFail");
        validator.validate(EXAMPLE_LONG, EXAMPLE_STRING, EXAMPLE_STRING, EXAMPLE_STATE);
    }

    @Test
    public void shouldThrowCorrectExceptionWhenPropertyValidationFail() {
        doThrow(new UserAccountPropertyException("propertyValidationFail"))
                .when(propertyValidator).validate(EXAMPLE_STRING, EXAMPLE_STRING, EXAMPLE_STATE);
        thrown.expect(UserAccountEditException.class);
        thrown.expectMessage("propertyValidationFail");
        validator.validate(EXAMPLE_LONG, EXAMPLE_STRING, EXAMPLE_STRING, EXAMPLE_STATE);
    }

    @Test
    public void shouldThrowCorrectExceptionWhenSeveralValidationFail() {
        doThrow(new IllegalArgumentException("inputValidationFail"))
                .when(inputValidator).validateUserAccountIdInput(EXAMPLE_LONG);
        doThrow(new UserAccountPropertyException("propertyValidationFail"))
                .when(propertyValidator).validate(EXAMPLE_STRING, EXAMPLE_STRING, EXAMPLE_STATE);
        thrown.expect(UserAccountEditException.class);
        thrown.expectMessage("inputValidationFail\npropertyValidationFail");
        validator.validate(EXAMPLE_LONG, EXAMPLE_STRING, EXAMPLE_STRING, EXAMPLE_STATE);
    }

}