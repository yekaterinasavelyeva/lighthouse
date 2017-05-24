package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.domain.ResourceType;
import lv.javaguru.java2.services.exceptions.ResourceEditException;
import lv.javaguru.java2.services.exceptions.ResourcePropertyException;
import lv.javaguru.java2.services.validators.DataExistValidator;
import lv.javaguru.java2.services.validators.DataInputValidator;
import lv.javaguru.java2.services.validators.ResourceEditValidator;
import lv.javaguru.java2.services.validators.ResourcePropertyValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.doThrow;

/**
 * Created by Viktor on 2017.05.24..
 */
@RunWith(MockitoJUnitRunner.class)
public class ResourceEditValidatorImplTest {

    private static final ResourceType TYPE = ResourceType.BOOK;
    private static final String TITLE = "title";
    private static final String AUTHOR = "author";
    private static final int RELEASE_YEAR = 2013;
    private static final Long RESOURCE_ID = 1234L;

    @Mock DataInputValidator dataInputValidator;
    @Mock DataExistValidator dataExistValidator;
    @Mock ResourcePropertyValidator resourcePropertyValidator;

    @InjectMocks
    private ResourceEditValidator validator = new ResourceEditValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowCorrectExceptionWithCollectedMessageCase1() {
        doThrow(new IllegalArgumentException("message1"))
                .when(dataInputValidator).validateResourceIdInput(RESOURCE_ID);
        doThrow(new ResourcePropertyException("message2"))
                .when(resourcePropertyValidator).validate(TYPE, TITLE, AUTHOR, RELEASE_YEAR);
        thrown.expect(ResourceEditException.class);
        thrown.expectMessage("message1\nmessage2");
        validator.validate(RESOURCE_ID, TYPE, TITLE, AUTHOR, RELEASE_YEAR);
    }

    @Test
    public void shouldThrowCorrectExceptionWithCollectedMessageCase2() {
        doThrow(new IllegalArgumentException("message1"))
                .when(dataExistValidator).validateResourceIdExists(RESOURCE_ID);
        doThrow(new ResourcePropertyException("message2"))
                .when(resourcePropertyValidator).validate(TYPE, TITLE, AUTHOR, RELEASE_YEAR);
        thrown.expect(ResourceEditException.class);
        thrown.expectMessage("message1\nmessage2");
        validator.validate(RESOURCE_ID, TYPE, TITLE, AUTHOR, RELEASE_YEAR);
    }

    @Test
    public void noExceptionIfValidationOK() {
        validator.validate(RESOURCE_ID,TYPE,TITLE,AUTHOR,RELEASE_YEAR);
    }

}