package lv.javaguru.java2.services.useraccount.validate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by user on 15.04.2017.
 */
public class UserAccountIdValidatorImplTest {

    private UserAccountIdValidator validator = new UserAccountIdValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void shouldThrowExceptionWhenAccountIDIsNullOrLessThanZero() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Account ID must not be empty or less than 0");
        validator.validate(null);
        validator.validate(-2l);
    }

}