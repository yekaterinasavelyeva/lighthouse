package lv.javaguru.java2.services.resource.impls;

import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.database.jdbc.ResourceDAOImpl;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.services.resource.GetAllResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by user on 17.04.2017.
 */
@Component
class GetAllResourcesServiceImpl implements GetAllResourcesService {

    @Autowired
    @Qualifier("HibernateResourceDAO")
    private ResourceDAO resourceDAO;

    @Override
    public List<Resource> getAllResourcesList(){
        List<Resource> resources = resourceDAO.getAll();
        return resources;
    }

    public void printMessage(String message){
        System.out.println(message);
    }

    // TODO: 2017.05.23. update this class (should be any validators?)
}
