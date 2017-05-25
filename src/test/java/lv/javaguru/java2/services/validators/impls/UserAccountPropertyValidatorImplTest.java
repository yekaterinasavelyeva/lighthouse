package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.domain.UserAccountState;
import lv.javaguru.java2.services.exceptions.UserAccountPropertyException;
import lv.javaguru.java2.services.validators.DataInputValidator;
import lv.javaguru.java2.services.validators.UserAccountPropertyValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.doThrow;

/**
 * Created by user on 26.03.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserAccountPropertyValidatorImplTest {
    private static final String FIRST_NAME = "first name";
    private static final String LAST_NAME = "last name";
    private static final UserAccountState STATE = UserAccountState.VISITOR;

    @Mock private DataInputValidator inputValidator;
    @InjectMocks
    private UserAccountPropertyValidator validator = new UserAccountPropertyValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowCorrectExceptionWhenFirstNameNotValid() {
        doThrow(new IllegalArgumentException("invalidFirstName"))
                .when(inputValidator).validateUserAccountFirstNameInput(FIRST_NAME);
        thrown.expect(UserAccountPropertyException.class);
        thrown.expectMessage("invalidFirstName");
        validator.validate(FIRST_NAME, LAST_NAME, STATE);
    }

    @Test
    public void shouldThrowCorrectExceptionWhenLastNameNotValid() {
        doThrow(new IllegalArgumentException("invalidLastName"))
                .when(inputValidator).validateUserAccountLastNameInput(LAST_NAME);
        thrown.expect(UserAccountPropertyException.class);
        thrown.expectMessage("invalidLastName");
        validator.validate(FIRST_NAME, LAST_NAME, STATE);
    }

    @Test
    public void shouldThrowCorrectExceptionWhenStateNotValid() {
        doThrow(new IllegalArgumentException("invalidState"))
                .when(inputValidator).validateUserAccountStateInput(STATE);
        thrown.expect(UserAccountPropertyException.class);
        thrown.expectMessage("invalidState");
        validator.validate(FIRST_NAME, LAST_NAME, STATE);
    }

    @Test
    public void shouldThrowCorrectExceptionWhenAllPropertiesNotValid() {
        doThrow(new IllegalArgumentException("invalidFirstName"))
                .when(inputValidator).validateUserAccountFirstNameInput(FIRST_NAME);
        doThrow(new IllegalArgumentException("invalidLastName"))
                .when(inputValidator).validateUserAccountLastNameInput(LAST_NAME);
        doThrow(new IllegalArgumentException("invalidState"))
                .when(inputValidator).validateUserAccountStateInput(STATE);
        thrown.expect(UserAccountPropertyException.class);
        thrown.expectMessage("invalidFirstName\n" +
                "invalidLastName\n" +
                "invalidState");
        validator.validate(FIRST_NAME, LAST_NAME, STATE);
    }

    @Test
    public void noExceptionWhenAllPropertiesAreValid() {
        validator.validate(FIRST_NAME, LAST_NAME, STATE);
    }

}