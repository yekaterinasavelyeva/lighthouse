package lv.javaguru.java2.services;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.database.jdbc.UserAccountDAOImpl;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.domain.UserAccountState;
import org.junit.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;

/**
 * Created by user on 26.03.2017.
 */
public class UserAccountServiceImplTest {

    private UserAccountDAO userAccountDAO = new UserAccountDAOImpl();
    UserAccountService service = new UserAccountServiceImpl();

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfUserNotFoundTest(){
        service.getUserAccount(1001l);
        service.edit(1001l, "firstname", "lastname", UserAccountState.ADMIN);
        service.deleteUserAccount(1001l);
    }

    @Test
    public void shouldReturnListOfUserAccountsTest(){
        List <UserAccount> accounts = service.getAllUsersList();
        List <UserAccount> accountsFromDatabase = userAccountDAO.getAll();
        UserAccount account = accounts.stream().findFirst().get();
        assertTrue(!accounts.isEmpty());
        assertTrue(accounts.size()== accountsFromDatabase.size());
    }

    @Test
    public void deleteUserFromDatabaseTest(){
        UserAccount account = userAccountDAO.getAll().stream().findFirst().get();
        service.deleteUserAccount(account.getUserAccountId());
        List <UserAccount> accounts = service.getAllUsersList();
        assertFalse(accounts.contains(account));
    }

    @Test
    public void getUserAccountByIDTest(){
        Long userAccountId = userAccountDAO.getAll().stream().findFirst().get().getUserAccountId();
        Optional <UserAccount> userAccountFromDB = userAccountDAO.getById(userAccountId);
        UserAccount account = service.getUserAccount(userAccountId);
        assertEquals(account.getUserAccountId(), userAccountFromDB.get().getUserAccountId());
        assertEquals(account.getFirstName(), userAccountFromDB.get().getFirstName());
        assertEquals(account.getLastName(), userAccountFromDB.get().getLastName());
        assertEquals(account.getState(), userAccountFromDB.get().getState());
    }

}