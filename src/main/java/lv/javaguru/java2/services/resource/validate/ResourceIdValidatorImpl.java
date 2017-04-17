package lv.javaguru.java2.services.resource.validate;

/**
 * Created by user on 17.04.2017.
 */
public class ResourceIdValidatorImpl implements ResourceIdValidator {
    public void validate(Long resourceID){
        validateResourceID(resourceID);
    }

    private  void validateResourceID(Long resourceId){
        if (resourceId == null || resourceId<0) {
            throw new IllegalArgumentException("Resource ID must not be empty or less than 0");
        }
    }
}
