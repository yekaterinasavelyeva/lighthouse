package lv.javaguru.java2.services.validate;

import lv.javaguru.java2.domain.ResourceType;

/**
 * Created by user on 11.04.2017.
 */
public interface ResourceValidator {
    void validate(ResourceType type, String title, String author, int releaseYear);
}
