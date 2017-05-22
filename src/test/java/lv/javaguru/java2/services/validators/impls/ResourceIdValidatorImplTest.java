package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.services.validators.ResourceIdValidator;
import lv.javaguru.java2.services.validators.impls.ResourceIdValidatorImpl;
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
 * Created by user on 17.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ResourceIdValidatorImplTest {

    private static final Long NULL_ID = null;
    private static final Long NEGATIVE_ID = -2l;
    private static final Long EXAMPLE_ID = 1234l;

    @Mock
    ResourceDAO resourceDAO;
    @InjectMocks
    ResourceIdValidator validator = new ResourceIdValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowExceptionWhenResourceIdIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Resource ID must not be empty or less than 0");
        validator.validate(NULL_ID);
    }

    @Test
    public void shouldThrowExceptionWhenResourceIdIsNegative() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Resource ID must not be empty or less than 0");
        validator.validate(NEGATIVE_ID);
    }

    @Test
    public void shouldThrowExceptionWhenResouceIdNotExist() {
        when(resourceDAO.getByID(EXAMPLE_ID))
                .thenReturn(Optional.empty());
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Resource not found by id = " + EXAMPLE_ID);
        validator.validate(EXAMPLE_ID);
    }

    @Test
    public void noExceptionWhenResourceIdExist() {
        when(resourceDAO.getByID(EXAMPLE_ID))
                .thenReturn(Optional.of(new Resource()));
        validator.validate(EXAMPLE_ID);
    }

}