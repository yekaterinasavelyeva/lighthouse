package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.domain.ResourceType;
import lv.javaguru.java2.domain.UserAccountState;
import lv.javaguru.java2.services.validators.DataInputValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by mobileqa on 28/04/17.
 */
public class DataInputValidatorImplTest {

    private static final Long NEGATIVE_ID = -2l;
    private static final Long POSITIVE_ID = 1234L;

    private DataInputValidator validator = new DataInputValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowExceptionWhenReservationStartDateIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Start date cannot be empty.");
        validator.validateStartDateInput(null);
    }

    @Test
    public void shouldThrowExceptionWhenReservationEndDateIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("End date cannot be empty.");
        validator.validateEndDateInput(null);
    }

    @Test
    public void shouldThrowExceptionWhenReservationIdIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservation ID cannot be null or negative");
        validator.validateReservationIdInput(null);
    }

    @Test
    public void shouldThrowExceptionWhenReservationIdIsNegative() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservation ID cannot be null or negative");
        validator.validateReservationIdInput(NEGATIVE_ID);
    }

    @Test
    public void noExceptionWhenReservationIdIsValid() {
        validator.validateReservationIdInput(POSITIVE_ID);
    }

    @Test
    public void shouldThrowExceptionWhenResourceIdIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Resource ID cannot be null or negative");
        validator.validateResourceIdInput(null);
    }

    @Test
    public void shouldThrowExceptionWhenResourceIdIsNegative() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Resource ID cannot be null or negative");
        validator.validateResourceIdInput(NEGATIVE_ID);
    }

    @Test
    public void noExceptionWhenResourceIdIsValid() {
        validator.validateResourceIdInput(POSITIVE_ID);
    }

    @Test
    public void shouldThrowExceptionWhenResourceTypeIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Resource Type cannot be empty!");
        validator.validateResourceTypeInput(null);
    }

    @Test
    public void noExceptionWhenResourceTypeIsValid() {
        validator.validateResourceTypeInput(ResourceType.ARTICLE);
    }

    @Test
    public void shouldThrowExceptionWhenResourceTitleIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Resource title cannot be empty!");
        validator.validateResourceTitleInput(null);
    }

    @Test
    public void shouldThrowExceptionWhenResourceTitleIsEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Resource title cannot be empty!");
        validator.validateResourceTitleInput("");
    }

    @Test
    public void noExceptionWhenResourceTitleIsValid() {
        validator.validateResourceTitleInput("smth");
    }

    @Test
    public void shouldThrowExceptionWhenResourceAuthorIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Resource Author cannot be empty!");
        validator.validateResourceAuthorInput(null);
    }

    @Test
    public void shouldThrowExceptionWhenResourceAuthorIsEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Resource Author cannot be empty!");
        validator.validateResourceAuthorInput("");
    }

    @Test
    public void noExceptionWhenResourceAuthorIsValid() {
        validator.validateResourceAuthorInput("smth");
    }

    @Test
    public void shouldThrowExceptionWhenUserAccountIdIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Account ID cannot be null or negative");
        validator.validateUserAccountIdInput(null);
    }

    @Test
    public void shouldThrowExceptionWhenUserAccountIdIsNegative() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Account ID cannot be null or negative");
        validator.validateUserAccountIdInput(NEGATIVE_ID);
    }

    @Test
    public void noExceptionWhenUserAccountIdIsValid() {
        validator.validateUserAccountIdInput(POSITIVE_ID);
    }

    @Test
    public void shouldThrowExceptionWhenUserAccountFirstNameIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("First Name cannot be empty!");
        validator.validateUserAccountFirstNameInput(null);
    }

    @Test
    public void shouldThrowExceptionWhenUserAccountFirstNameIsEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("First Name cannot be empty!");
        validator.validateUserAccountFirstNameInput("");
    }

    @Test
    public void noExceptionWhenUserAccountFirstNameIsValid() {
        validator.validateUserAccountFirstNameInput("smth");
    }

    @Test
    public void shouldThrowExceptionWhenUserAccountLastNameIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Last Name cannot be empty!");
        validator.validateUserAccountLastNameInput(null);
    }

    @Test
    public void shouldThrowExceptionWhenUserAccountLastNameIsEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Last Name cannot be empty!");
        validator.validateUserAccountLastNameInput("");
    }

    @Test
    public void noExceptionWhenUserAccountLastNameIsValid() {
        validator.validateUserAccountLastNameInput("smth");
    }

    @Test
    public void shouldThrowExceptionWhenUserAccountStateIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Account type cannot be empty!");
        validator.validateUserAccountStateInput(null);
    }

    @Test
    public void noExceptionWhenUserAccountStateIsValid() {
        validator.validateUserAccountStateInput(UserAccountState.ADMIN);
    }
}