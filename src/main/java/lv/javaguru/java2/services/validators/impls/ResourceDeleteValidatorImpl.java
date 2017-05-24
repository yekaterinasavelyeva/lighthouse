package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.services.exceptions.ResourceDeleteException;
import lv.javaguru.java2.services.validators.ResourceDeleteValidator;
import lv.javaguru.java2.services.validators.DataInputValidator;
import lv.javaguru.java2.services.validators.LibraryRuleValidator;
import lv.javaguru.java2.services.validators.DataExistValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Viktor on 2017.05.22..
 */
@Component
class ResourceDeleteValidatorImpl implements ResourceDeleteValidator {

    @Autowired
    private DataExistValidator dataExistValidator;
    @Autowired
    private DataInputValidator dataInputValidator;
    @Autowired
    private LibraryRuleValidator ruleValidator;

    @Override
    public void validate(Long resourceId) {
        try {
            dataInputValidator.validateResourceIdInput(resourceId);
            dataExistValidator.validateResourceIdExists(resourceId);
            ruleValidator.validateResourceReservationStatusWhenDeletingResource(resourceId);
        } catch (IllegalArgumentException e) {
            throw new ResourceDeleteException(e.getMessage());
        }
    }
}
