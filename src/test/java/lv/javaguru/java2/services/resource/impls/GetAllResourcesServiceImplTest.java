package lv.javaguru.java2.services.resource.impls;

import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.services.resource.GetAllResourcesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

    @Mock
    private ResourceDAO resourceDAO;
    @InjectMocks
    private GetAllResourcesService service = new GetAllResourcesServiceImpl();

    @Test
    public void shouldReturnListOfResourcesTest(){
        when(resourceDAO.getAll()).thenReturn(Arrays.asList(new Resource(), new Resource()));
        List<Resource> resources = service.getAllResourcesList();
        List <Resource> resourcesFromDatabase = resourceDAO.getAll();

        assertTrue(!resources.isEmpty());
        assertTrue(resources.size()== resourcesFromDatabase.size());
    }

}