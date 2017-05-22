package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.services.exceptions.DeleteResourceException;
import lv.javaguru.java2.services.validators.DeleteResourceValidator;
import lv.javaguru.java2.services.validators.InputValidator;
import lv.javaguru.java2.services.validators.ResourceRuleValidator;
import lv.javaguru.java2.services.validators.SearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Viktor on 2017.05.22..
 */
@Component
class DeleteResourceValidatorImpl implements DeleteResourceValidator {

    @Autowired
    private SearchValidator searchValidator;
    @Autowired
    private InputValidator inputValidator;
    @Autowired
    private ResourceRuleValidator ruleValidator;

    @Override
    public void validate(Long resourceId) {
        try {
            inputValidator.validateResourceIdInput(resourceId);
            searchValidator.validateResourceIdExist(resourceId);
            ruleValidator.validateReservationStatusForResource(resourceId);
        } catch (IllegalArgumentException e) {
            throw new DeleteResourceException(e.getMessage());
        }
    }
}
