package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.services.exceptions.ResourceDeleteException;
import lv.javaguru.java2.services.validators.ResourceDeleteValidator;
import lv.javaguru.java2.services.validators.DataInputValidator;
import lv.javaguru.java2.services.validators.LibraryRuleValidator;
import lv.javaguru.java2.services.validators.DataExistValidator;
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
 * Created by Viktor on 2017.05.22..
 */
@RunWith(MockitoJUnitRunner.class)
public class ResourceDeleteValidatorImplTest {
    @Mock private DataInputValidator dataInputValidator;
    @Mock private DataExistValidator dataExistValidator;
    @Mock private LibraryRuleValidator ruleValidator;


    @InjectMocks
    private ResourceDeleteValidator validator = new ResourceDeleteValidatorImpl();

    private static final Long resourceId = 1234l;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void checkValidationMethodsOrder() {
        InOrder inOrder = Mockito.inOrder(dataInputValidator, dataExistValidator, ruleValidator);
        validator.validate(resourceId);
        inOrder.verify(dataInputValidator).validateResourceIdInput(resourceId);
        inOrder.verify(dataExistValidator).validateResourceIdExists(resourceId);
        inOrder.verify(ruleValidator).validateResourceReservationStatusWhenDeletingResource(resourceId);
    }

    @Test
    public void shouldThrowCorrectExceptionTypeWhenInputValidationFailed(){
        doThrow(new IllegalArgumentException("smth"))
                .when(dataInputValidator).validateResourceIdInput(resourceId);
        thrown.expect(ResourceDeleteException.class);
        thrown.expectMessage("smth");
        validator.validate(resourceId);
    }

    @Test
    public void shouldThrowCorrectExceptionTypeWhenSearchValidationFailed(){
        doThrow(new IllegalArgumentException("smth"))
                .when(dataExistValidator).validateResourceIdExists(resourceId);
        thrown.expect(ResourceDeleteException.class);
        thrown.expectMessage("smth");
        validator.validate(resourceId);
    }

    @Test
    public void shouldThrowCorrectExceptionTypeWhenRuleValidationFailed(){
        doThrow(new IllegalArgumentException("smth"))
                .when(ruleValidator).validateResourceReservationStatusWhenDeletingResource(resourceId);
        thrown.expect(ResourceDeleteException.class);
        thrown.expectMessage("smth");
        validator.validate(resourceId);
    }

}