package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.domain.Resource;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by user on 20.05.2017.
 */
@Component("HibernateResourceDAO")
@Transactional
public class ResourceDAOImpl implements ResourceDAO {

    public Resource save(Resource resource){return new Resource();}

    public Optional<Resource> getByID(Long resourceID){return Optional.of(new Resource());}

    public void delete(Long resourceID){}

    public void update (Resource resource){}

    public List<Resource> getAll(){return new ArrayList<Resource>();}

}
