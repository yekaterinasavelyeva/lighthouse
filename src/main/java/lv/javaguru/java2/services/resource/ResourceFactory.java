package lv.javaguru.java2.services.resource;

import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.domain.ResourceType;

/**
 * Created by user on 11.04.2017.
 */
public interface ResourceFactory {
    Resource create(ResourceType type, String title, String author, int releaseYear);
}
