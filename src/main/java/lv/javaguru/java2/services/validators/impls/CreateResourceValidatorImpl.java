package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.domain.ResourceType;
import lv.javaguru.java2.services.exceptions.CreateResourceException;
import lv.javaguru.java2.services.validators.CreateResourceValidator;
import lv.javaguru.java2.services.validators.InputValidator;
import lv.javaguru.java2.services.validators.ResourceRuleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by user on 11.04.2017.
 */
@Component
class CreateResourceValidatorImpl implements CreateResourceValidator {

    @Autowired
    private InputValidator inputValidator;
    @Autowired
    private ResourceRuleValidator ruleValidator;

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
            inputValidator.validateResourceTypeInput(type);
        } catch (IllegalArgumentException e) {
            collectMessage(e.getMessage());
        }
    }

    private void validateTitle(String title){
        try {
            inputValidator.validateResourceTitleInput(title);
        } catch (IllegalArgumentException e) {
            collectMessage(e.getMessage());
        }
    }

    private void validateAuthor(String author){
        try {
            inputValidator.validateResourceAuthorInput(author);
        } catch (IllegalArgumentException e) {
            collectMessage(e.getMessage());
        }
    }

    private void validateReleaseYear(int releaseYear){
        try {
            ruleValidator.validateReleaseYearForNewResource(releaseYear);
        } catch (IllegalArgumentException e) {
            collectMessage(e.getMessage());
        }
    }

    private void handleValidationErrors() {
        String errors = validationErrors.toString();
        if (!errors.isEmpty()) {
            throw new CreateResourceException(errors);
        }
    }

    private void collectMessage(String message) {
        validationErrors.append(message).append("\n");
    }
}
