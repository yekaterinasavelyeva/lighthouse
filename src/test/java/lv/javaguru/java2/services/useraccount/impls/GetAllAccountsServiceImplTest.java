package lv.javaguru.java2.services.useraccount.impls;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.services.useraccount.GetAllAccountsService;
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
 * Created by user on 07.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class GetAllAccountsServiceImplTest {

    @Mock
    private UserAccountDAO userAccountDAO;
    @InjectMocks
    private GetAllAccountsService service = new GetAllAccountsServiceImpl();

    @Test
    public void shouldReturnListOfUserAccountsTest(){
        when(userAccountDAO.getAll()).thenReturn(Arrays.asList(new UserAccount(), new UserAccount()));
        List<UserAccount> accounts = service.getAllAccountsList();
        List <UserAccount> accountsFromDatabase = userAccountDAO.getAll();

        assertTrue(!accounts.isEmpty());
        assertTrue(accounts.size()== accountsFromDatabase.size());
    }
}