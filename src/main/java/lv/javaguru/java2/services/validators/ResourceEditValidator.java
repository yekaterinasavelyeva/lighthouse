package lv.javaguru.java2.services.validators;

import lv.javaguru.java2.domain.ResourceType;

/**
 * Created by Viktor on 2017.05.24..
 */
public interface ResourceEditValidator {
    void validate(Long resourceId, ResourceType resourceType, String resourceTitle,
                  String resourceAuthor, int releaseYear);
}
