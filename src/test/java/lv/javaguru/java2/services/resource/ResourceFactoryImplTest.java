package lv.javaguru.java2.services.resource;

import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.database.jdbc.ResourceDAOImpl;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.domain.ResourceType;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by user on 11.04.2017.
 */
public class ResourceFactoryImplTest {

    ResourceFactory factory = new ResourceFactoryImpl();
    private ResourceDAO resourceDAO = new ResourceDAOImpl();

    @Test
    public void createResource() {
        Resource resource = factory.create(ResourceType.BOOK,"Game of Thrones", "George R. Martin", 2012);
        Optional<Resource> resourceFromDB = resourceDAO.getByID(resource.getResourceID());
        assertTrue(resourceFromDB.isPresent());
        assertEquals(resource.getResourceID(), resourceFromDB.get().getResourceID());
        assertEquals(resource.getResourceType(), resourceFromDB.get().getResourceType());
        assertEquals(resource.getTitle(), resourceFromDB.get().getTitle());
        assertEquals(resource.getAuthor(), resourceFromDB.get().getAuthor());
        assertEquals(resource.getReleaseYear(), resourceFromDB.get().getReleaseYear());
    }

}