package lv.javaguru.java2.services.useraccount;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.database.jdbc.UserAccountDAOImpl;
import lv.javaguru.java2.domain.UserAccount;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by user on 07.04.2017.
 */
public class GetAllUsersServiceImplTest {

    private UserAccountDAO userAccountDAO = new UserAccountDAOImpl();
    GetAllUsersService service = new GetAllUsersServiceImpl();

    @Test
    public void shouldReturnListOfUserAccountsTest(){
        List<UserAccount> accounts = service.getAllUsersList();
        List <UserAccount> accountsFromDatabase = userAccountDAO.getAll();
        UserAccount account = accounts.stream().findFirst().get();
        assertTrue(!accounts.isEmpty());
        assertTrue(accounts.size()== accountsFromDatabase.size());
    }
}