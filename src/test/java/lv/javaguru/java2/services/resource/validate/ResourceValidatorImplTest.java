package lv.javaguru.java2.services.resource.validate;

import lv.javaguru.java2.domain.ResourceType;
import lv.javaguru.java2.services.resource.validate.ResourceValidator;
import lv.javaguru.java2.services.resource.validate.ResourceValidatorImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by user on 11.04.2017.
 */
public class ResourceValidatorImplTest {

    private ResourceValidator validator = new ResourceValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void shouldThrowExceptionWhenResourceTypeIsNull() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Resource Type cannot be empty!");
        validator.validate(null, "Game Of Thrones", "George R. Martin", 2012);
    }

    @Test
    public void shouldThrowExceptionWhenTitleIsNullOrEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Resource title cannot be empty!");
        validator.validate(ResourceType.BOOK, null, "George R. Martin", 2012);
        validator.validate(ResourceType.BOOK, "", "George R. Martin", 2012);
    }

    @Test
    public void shouldThrowExceptionWhenAuthorIsNullOrEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Resource Author cannot be empty!");
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