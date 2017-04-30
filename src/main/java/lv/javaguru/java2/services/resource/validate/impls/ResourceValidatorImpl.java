package lv.javaguru.java2.services.resource.validate.impls;

import lv.javaguru.java2.domain.ResourceType;
import lv.javaguru.java2.services.resource.validate.ResourceValidator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by user on 11.04.2017.
 */
@Component
public class ResourceValidatorImpl implements ResourceValidator {

    private StringBuilder validationErrors = new StringBuilder();

    @Override
    public void validate(ResourceType type, String title, String author, int releaseYear){
        validateResourceType(type);
        validateTitle(title);
        validateAuthor(author);
        validateReleaseYear(releaseYear);
        handleValidationErrors();

    }

    private void validateResourceType(ResourceType type){
        if (type == null) {
            validationErrors.append("Resource Type cannot be empty!\n");
        }
    }

    private void validateTitle(String title){
        if (title == null || title.isEmpty()) {
            validationErrors.append("Resource title cannot be empty!\n");
        }
    }

    private void validateAuthor(String author){
        if (author == null || author.isEmpty()) {
            validationErrors.append("Resource Author cannot be empty!\n");
        }
    }

    private void validateReleaseYear(int releaseYear){
        if (releaseYear < 1 || releaseYear > LocalDate.now().getYear()) {
            validationErrors.append("Release Year cannot be 0 or greater than current year!\n");
        }
    }

    private void handleValidationErrors() {
        String errors = validationErrors.toString();
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(errors);
        }
    }
}
