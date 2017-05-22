package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.services.exceptions.DeleteResourceException;
import lv.javaguru.java2.services.validators.DeleteResourceValidator;
import lv.javaguru.java2.services.validators.InputValidator;
import lv.javaguru.java2.services.validators.ResourceRuleValidator;
import lv.javaguru.java2.services.validators.SearchValidator;
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
 * Created by Viktor on 2017.05.22..
 */
@RunWith(MockitoJUnitRunner.class)
public class DeleteResourceValidatorImplTest {
    @Mock private InputValidator inputValidator;
    @Mock private SearchValidator searchValidator;
    @Mock private ResourceRuleValidator ruleValidator;


    @InjectMocks
    private DeleteResourceValidator validator = new DeleteResourceValidatorImpl();

    private static final Long resourceId = 1234l;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void checkValidationMethodsOrder() {
        InOrder inOrder = Mockito.inOrder(inputValidator, searchValidator, ruleValidator);
        validator.validate(resourceId);
        inOrder.verify(inputValidator).validateResourceIdInput(resourceId);
        inOrder.verify(searchValidator).validateResourceIdExist(resourceId);
        inOrder.verify(ruleValidator).validateReservationStatusForResource(resourceId);
    }

    @Test
    public void shouldThrowCorrectExceptionTypeWhenInputValidationFailed(){
        doThrow(new IllegalArgumentException("smth"))
                .when(inputValidator).validateResourceIdInput(resourceId);
        thrown.expect(DeleteResourceException.class);
        thrown.expectMessage("smth");
        validator.validate(resourceId);
    }

    @Test
    public void shouldThrowCorrectExceptionTypeWhenSearchValidationFailed(){
        doThrow(new IllegalArgumentException("smth"))
                .when(searchValidator).validateResourceIdExist(resourceId);
        thrown.expect(DeleteResourceException.class);
        thrown.expectMessage("smth");
        validator.validate(resourceId);
    }

    @Test
    public void shouldThrowCorrectExceptionTypeWhenRuleValidationFailed(){
        doThrow(new IllegalArgumentException("smth"))
                .when(ruleValidator).validateReservationStatusForResource(resourceId);
        thrown.expect(DeleteResourceException.class);
        thrown.expectMessage("smth");
        validator.validate(resourceId);
    }

}