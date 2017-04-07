package lv.javaguru.java2.services.validate;

import lv.javaguru.java2.domain.UserAccountState;
import lv.javaguru.java2.services.validate.UserAccountValidator;
import lv.javaguru.java2.services.validate.UserAccountValidatorImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by user on 26.03.2017.
 */
public class UserAccountValidatorImplTest {

    private UserAccountValidator validator = new UserAccountValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void shouldThrowExceptionWhenFirstNameIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("First Name must be not empty!");
        validator.validate(null, "lastname", UserAccountState.ADMIN);
    }

    @Test
    public void shouldThrowExceptionWhenFirstNameIsEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("First Name must be not empty!");
        validator.validate("", "lastname", UserAccountState.VISITOR);
    }

    @Test
    public void shouldThrowExceptionWhenLastNameIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Last Name must be not empty!");
        validator.validate("firstname",null, UserAccountState.ADMIN);
    }

    @Test
    public void shouldThrowExceptionWhenLastNameIsEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Last Name must be not empty!");
        validator.validate("firstname", "", UserAccountState.VISITOR);
    }

    @Test
    public void shouldThrowExceptionWhenStateIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Account type must be not empty!");
        validator.validate("firstname", "lastname", null);
    }


    // write more tests


}