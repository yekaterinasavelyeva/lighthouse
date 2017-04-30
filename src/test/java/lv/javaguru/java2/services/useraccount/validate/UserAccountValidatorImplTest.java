package lv.javaguru.java2.services.useraccount.validate;

import lv.javaguru.java2.domain.UserAccountState;
import lv.javaguru.java2.services.useraccount.validate.impls.UserAccountValidatorImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by user on 26.03.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserAccountValidatorImplTest {

    @InjectMocks
    private UserAccountValidator validator = new UserAccountValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void shouldThrowExceptionWhenFirstNameIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("First Name cannot be empty!");
        validator.validate(null, "lastname", UserAccountState.ADMIN);
    }

    @Test
    public void shouldThrowExceptionWhenFirstNameIsEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("First Name cannot be empty!");
        validator.validate("", "lastname", UserAccountState.VISITOR);
    }

    @Test
    public void shouldThrowExceptionWhenLastNameIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Last Name cannot be empty!");
        validator.validate("firstname",null, UserAccountState.ADMIN);
    }

    @Test
    public void shouldThrowExceptionWhenLastNameIsEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Last Name cannot be empty!");
        validator.validate("firstname", "", UserAccountState.VISITOR);
    }

    @Test
    public void shouldThrowExceptionWhenStateIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Account type cannot be empty!");
        validator.validate("firstname", "lastname", null);
    }

    @Test
    public void shouldThrowExceptionWithCorrectMessageWhenAllIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("First Name cannot be empty!\n" +
                "Last Name cannot be empty!\n" +
                "Account type cannot be empty!");
        validator.validate(null, null, null);
    }

    @Test
    public void shouldThrowExceptionWithCorrectMessageWhenAllIsEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("First Name cannot be empty!\n" +
                "Last Name cannot be empty!\n" +
                "Account type cannot be empty!");
        validator.validate("", "", null);
    }
}