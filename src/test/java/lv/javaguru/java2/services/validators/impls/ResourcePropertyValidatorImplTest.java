package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.domain.ResourceType;
import lv.javaguru.java2.services.exceptions.ResourcePropertyException;
import lv.javaguru.java2.services.validators.ResourcePropertyValidator;
import lv.javaguru.java2.services.validators.DataInputValidator;
import lv.javaguru.java2.services.validators.LibraryRuleValidator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.doThrow;

/**
 * Created by user on 11.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ResourcePropertyValidatorImplTest {

    private static final ResourceType NULL_TYPE = null;
    private static final String NULL_TITLE = null;
    private static final String NULL_AUTHOR = null;

    @Mock
    DataInputValidator dataInputValidator;
    @Mock
    LibraryRuleValidator ruleValidator;

    @InjectMocks
    ResourcePropertyValidator validator = new ResourcePropertyValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        doThrow(new IllegalArgumentException("smth1"))
                .when(dataInputValidator).validateResourceTypeInput(null);
        doThrow(new IllegalArgumentException("smth2"))
                .when(dataInputValidator).validateResourceTitleInput(null);
        doThrow(new IllegalArgumentException("smth3"))
                .when(dataInputValidator).validateResourceAuthorInput(null);
        doThrow(new IllegalArgumentException("smth4"))
                .when(ruleValidator).validateResourceReleaseYearLimits(0);
    }

    @Test
    public void shouldThrowExceptionWithCorrectMessageWhenAllIsNull() {
        thrown.expect(ResourcePropertyException.class);
        thrown.expectMessage("smth1\n" +
                "smth2\n" +
                "smth3\n" +
                "smth4");
        validator.validate(null, null, null, 0);
    }
}