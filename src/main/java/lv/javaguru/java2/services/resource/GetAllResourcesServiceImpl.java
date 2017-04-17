package lv.javaguru.java2.services.resource;

import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.database.jdbc.ResourceDAOImpl;
import lv.javaguru.java2.domain.Resource;

import java.util.List;

/**
 * Created by user on 17.04.2017.
 */
public class GetAllResourcesServiceImpl implements GetAllResourcesService {

    private ResourceDAO resourceDAO = new ResourceDAOImpl();

    public GetAllResourcesServiceImpl(ResourceDAO dao){
        resourceDAO = dao;
    }

    @Override
    public List<Resource> getAllResourcesList(){
        List<Resource> resources = resourceDAO.getAll();
        return resources;
    }

    public void printMessage(String message){
        System.out.println(message);
    }
}
