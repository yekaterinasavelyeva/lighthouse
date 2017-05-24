package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.domain.ResourceType;
import lv.javaguru.java2.services.exceptions.ResourceEditException;
import lv.javaguru.java2.services.exceptions.ResourcePropertyException;
import lv.javaguru.java2.services.validators.DataExistValidator;
import lv.javaguru.java2.services.validators.DataInputValidator;
import lv.javaguru.java2.services.validators.ResourceEditValidator;
import lv.javaguru.java2.services.validators.ResourcePropertyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Viktor on 2017.05.24..
 */
@Component
class ResourceEditValidatorImpl implements ResourceEditValidator {
    @Autowired private DataInputValidator dataInputValidator;
    @Autowired private DataExistValidator dataExistValidator;
    @Autowired private ResourcePropertyValidator resourcePropertyValidator;

    private StringBuilder validationErrors = new StringBuilder();

    @Override
    public void validate(Long id, ResourceType type, String title, String author, int releaseYear) {
        validateResourceId(id);
        validateResourceProperties(type, title, author, releaseYear);
        handleValidationErrors();
    }

    private void validateResourceId(Long resourceId) {
        try {
            dataInputValidator.validateResourceIdInput(resourceId);
            dataExistValidator.validateResourceIdExists(resourceId);
        } catch (IllegalArgumentException e) {
            collectMessage(e.getMessage());
        }
    }

    private void validateResourceProperties(ResourceType type, String title, String author, int releaseYear) {
        try {
            resourcePropertyValidator.validate(type, title, author, releaseYear);
        } catch (ResourcePropertyException e) {
            collectMessage(e.getMessage());
        }
    }

    private  void handleValidationErrors() {
        String errors = validationErrors.toString();
        if(!errors.isEmpty()) {
            throw new ResourceEditException(errors);
        }
    }

    private void collectMessage(String message) {
        validationErrors.append(message).append("\n");
    }
}
