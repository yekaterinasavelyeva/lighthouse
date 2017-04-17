package lv.javaguru.java2.services.useraccount;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.domain.UserAccount;
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
 * Created by user on 07.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class GetAllAccountsServiceImplTest {

    private UserAccountDAO userAccountDAO;
    private GetAllAccountsService service;

    @Before
    public void init() {
        userAccountDAO = mock(UserAccountDAO.class);
        service = new GetAllAccountsServiceImpl(userAccountDAO);
    }

    @Test
    public void shouldReturnListOfUserAccountsTest(){
        when(userAccountDAO.getAll()).thenReturn(Arrays.asList(new UserAccount(), new UserAccount()));
        List<UserAccount> accounts = service.getAllAccountsList();
        List <UserAccount> accountsFromDatabase = userAccountDAO.getAll();

        assertTrue(!accounts.isEmpty());
        assertTrue(accounts.size()== accountsFromDatabase.size());
    }
}