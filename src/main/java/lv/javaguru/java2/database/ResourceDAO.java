package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Resource;

import java.util.List;
import java.util.Optional;

/**
 * Created by VBarbasins on 27/03/17.
 */
public interface ResourceDAO {

    Resource save(Resource resource);

    Optional<Resource> getByID(Long resourceID);

    void delete(Long resourceID);

    void update (Resource resource);

    List<Resource> getAll();

}
