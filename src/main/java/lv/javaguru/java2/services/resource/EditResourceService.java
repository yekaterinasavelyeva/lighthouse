package lv.javaguru.java2.services.resource;

import lv.javaguru.java2.domain.ResourceType;

/**
 * Created by user on 17.04.2017.
 */
public interface EditResourceService {
    void edit(Long resourceId, ResourceType type,  String title, String author, int releaseYear);
}
