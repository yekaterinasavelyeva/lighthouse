package lv.javaguru.java2.services.resource;

import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.domain.Resource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by user on 17.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class GetAllResourcesServiceImplTest {

    private ResourceDAO resourceDAO;
    private GetAllResourcesService service;

    @Before
    public void init() {
        resourceDAO = mock(ResourceDAO.class);
        service = new GetAllResourcesServiceImpl(resourceDAO);
    }

    @Test
    public void shouldReturnListOfResourcesTest(){
        when(resourceDAO.getAll()).thenReturn(Arrays.asList(new Resource(), new Resource()));
        List<Resource> resources = service.getAllResourcesList();
        List <Resource> resourcesFromDatabase = resourceDAO.getAll();

        assertTrue(!resources.isEmpty());
        assertTrue(resources.size()== resourcesFromDatabase.size());
    }

}