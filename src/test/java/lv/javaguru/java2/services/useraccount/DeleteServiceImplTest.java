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
public class DeleteServiceImplTest {

    private UserAccountDAO userAccountDAO = new UserAccountDAOImpl();
    DeleteService service = new DeleteServiceImpl();

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfUserNotFoundTest(){

        service.deleteUserAccount(1001l);
    }

    @Test
    public void deleteUserFromDatabaseTest(){
        UserAccount account = userAccountDAO.getAll().stream().findFirst().get();
        service.deleteUserAccount(account.getUserAccountId());
        List<UserAccount> accounts = userAccountDAO.getAll();
        assertFalse(accounts.contains(account));
    }

}