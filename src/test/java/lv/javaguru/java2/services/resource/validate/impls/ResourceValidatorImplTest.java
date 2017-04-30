package lv.javaguru.java2.services.resource.validate.impls;

import lv.javaguru.java2.domain.ResourceType;
import lv.javaguru.java2.services.resource.validate.ResourceValidator;
import lv.javaguru.java2.services.resource.validate.impls.ResourceValidatorImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by user on 11.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ResourceValidatorImplTest {

    private static final ResourceType NULL_TYPE = null;
    private static final String NULL_TITLE = null;
    private static final String NULL_AUTHOR = null;

    @InjectMocks
    ResourceValidator validator = new ResourceValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void shouldThrowExceptionWhenResourceTypeIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Resource Type cannot be empty!");
        validator.validate(NULL_TYPE, "Game Of Thrones", "George R. Martin", 2012);
    }

    @Test
    public void shouldThrowExceptionWhenTitleIsNullOrEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Resource title cannot be empty!");
        validator.validate(ResourceType.BOOK, NULL_TITLE, "George R. Martin", 2012);
        validator.validate(ResourceType.BOOK, "", "George R. Martin", 2012);
    }

    @Test
    public void shouldThrowExceptionWhenAuthorIsNullOrEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Resource Author cannot be empty!");
        validator.validate(ResourceType.BOOK, "Game Of Thrones", NULL_AUTHOR, 2012);
        validator.validate(ResourceType.BOOK, "Game Of Thrones", "", 2012);
    }

    @Test
    public void shouldThrowExceptionWhenReleaseYearIsWrong() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Release Year cannot be 0 or greater than current year!");
        validator.validate(ResourceType.BOOK, "Game Of Thrones", "George R. Martin", -1);
        validator.validate(ResourceType.BOOK, "Game Of Thrones", "George R. Martin", 2035);
    }

    @Test
    public void shouldThrowExceptionWithCorrectMessageWhenAllIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Resource Type cannot be empty!\n" +
                "Resource title cannot be empty!\n" +
                "Resource Author cannot be empty!\n" +
                "Release Year cannot be 0 or greater than current year!");
        validator.validate(null, null, null, 0);
    }
}