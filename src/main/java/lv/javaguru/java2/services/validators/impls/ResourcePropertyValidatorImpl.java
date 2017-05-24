package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.domain.ResourceType;
import lv.javaguru.java2.services.exceptions.ResourcePropertyException;
import lv.javaguru.java2.services.validators.ResourcePropertyValidator;
import lv.javaguru.java2.services.validators.DataInputValidator;
import lv.javaguru.java2.services.validators.LibraryRuleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by user on 11.04.2017.
 */
@Component
class ResourcePropertyValidatorImpl implements ResourcePropertyValidator {

    @Autowired
    private DataInputValidator dataInputValidator;
    @Autowired
    private LibraryRuleValidator ruleValidator;

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
        try {
            dataInputValidator.validateResourceTypeInput(type);
        } catch (IllegalArgumentException e) {
            collectMessage(e.getMessage());
        }
    }

    private void validateTitle(String title){
        try {
            dataInputValidator.validateResourceTitleInput(title);
        } catch (IllegalArgumentException e) {
            collectMessage(e.getMessage());
        }
    }

    private void validateAuthor(String author){
        try {
            dataInputValidator.validateResourceAuthorInput(author);
        } catch (IllegalArgumentException e) {
            collectMessage(e.getMessage());
        }
    }

    private void validateReleaseYear(int releaseYear){
        try {
            ruleValidator.validateResourceReleaseYearLimits(releaseYear);
        } catch (IllegalArgumentException e) {
            collectMessage(e.getMessage());
        }
    }

    private void handleValidationErrors() {
        String errors = validationErrors.toString();
        if (!errors.isEmpty()) {
            throw new ResourcePropertyException(errors);
        }
    }

    private void collectMessage(String message) {
        validationErrors.append(message).append("\n");
    }
}
