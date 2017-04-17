package lv.javaguru.java2.services.resource.validate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by user on 17.04.2017.
 */
public class ResourceIdValidatorImplTest {
    private ResourceIdValidator validator = new ResourceIdValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowExceptionWhenResourceIDIsNullOrLessThanZero() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Resource ID must not be empty or less than 0");
        validator.validate(null);
        validator.validate(-2l);
    }

}