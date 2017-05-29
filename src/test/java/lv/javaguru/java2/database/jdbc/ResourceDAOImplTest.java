package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.domain.ResourceType;
import lv.javaguru.java2.servlet.mvc.SpringAppConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

import static lv.javaguru.java2.domain.ResourceBuilder.createResource;
import static org.junit.Assert.*;

/**
 * Created by VBarbasins on 27/03/17.
 */
@Transactional
@Component
public class ResourceDAOImplTest  extends DBUnitTestCase {
    @Autowired
    @Qualifier("HibernateResourceDAO")
    private ResourceDAO resourceDAO;

    private Resource resource;
    private Optional<Resource> resourceFromDB;

    @Override
    protected String getDatabaseFile() {
        return "dbscripts/ResourceDAOImplTest.xml";
    }

    @Before
    public void init(){
        ApplicationContext springContext =
                new AnnotationConfigApplicationContext(SpringAppConfig.class);
        resourceDAO = springContext.getBean(ResourceDAO.class);
    }

    @Test
    public void testSave() throws DBException {
        prepareResourceAsNew();
        resourceDAO.save(resource);
        getResourceFromDB();
        assertTrue(resourceFromDB.isPresent());
        assertEqualsAllProperties(resource, resourceFromDB.get());
    }

    @Test
    public void testUpdate() throws DBException {
        prepareResourceAsFirstFromDB();
        updateResourceProperties();
        resourceDAO.update(resource);
        getResourceFromDB();
        assertTrue(resourceFromDB.isPresent());
        assertEqualsAllProperties(resource, resourceFromDB.get());
    }

    @Test
    public void testDelete() throws DBException {
        prepareResourceAsFirstFromDB();
        resourceDAO.delete(resource.getResourceId());
        getResourceFromDB();
        assertFalse(resourceFromDB.isPresent());
    }

    private void assertEqualsAllProperties(Resource a, Resource b) {
        assertEquals(a.getResourceId(), b.getResourceId());
        assertEquals(a.getResourceType(), b.getResourceType());
        assertEquals(a.getTitle(), b.getTitle());
        assertEquals(a.getAuthor(), b.getAuthor());
        assertEquals(a.getReleaseYear(), b.getReleaseYear());
    }

    private void getResourceFromDB() {
        resourceFromDB = resourceDAO.getByID(resource.getResourceId());
    }

    private void prepareResourceAsNew() {
        resource = createResource()
                .withResourceType(ResourceType.BOOK)
                .withTitle("testTitle")
                .withAuthor("testAuthor")
                .withReleaseYear(2017).build();
    }

    private void prepareResourceAsFirstFromDB() {
        resource = resourceDAO.getAll().stream().findFirst().get();
    }

    private void updateResourceProperties() {
        resource.setTitle("another title");
        resource.setAuthor("another author");
        resource.setResourceType(ResourceType.JOURNAL);
        resource.setReleaseYear(1999);
    }
}