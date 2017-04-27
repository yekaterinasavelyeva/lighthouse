package lv.javaguru.java2.services.useraccount.validate;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.services.useraccount.validate.validatorsImpls.UserAccountIdValidatorImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 * Created by user on 15.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserAccountIdValidatorImplTest {

    private final static Long NULL_ID = null;
    private final static Long NEGATIVE_ID = -2l;
    private final static Long EXAMPLE_ID = 1234l;

    @Mock
    UserAccountDAO userAccountDAO;
    @InjectMocks
    private UserAccountIdValidator validator = new UserAccountIdValidatorImpl();
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowExceptionWhenUserAccountIdIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Account ID must not be empty or less than 0");
        validator.validate(NULL_ID);
        validator.validate(-2l);
    }

    @Test
    public void shouldThrowExceptionWhenUserAccountIdIsNegative() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Account ID must not be empty or less than 0");
        validator.validate(NEGATIVE_ID);
    }

    @Test
    public void shouldThrowExceptionWhenUserAccountIdNotExist() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User account not found id = " + EXAMPLE_ID);
        when(userAccountDAO.getById(EXAMPLE_ID))
                .thenReturn(Optional.empty());
        validator.validate(EXAMPLE_ID);
    }

    @Test
    public void noExceptionWhenUserAccountIdExist() {
        when(userAccountDAO.getById(EXAMPLE_ID))
                .thenReturn(Optional.of(new UserAccount()));
        validator.validate(EXAMPLE_ID);
    }

}