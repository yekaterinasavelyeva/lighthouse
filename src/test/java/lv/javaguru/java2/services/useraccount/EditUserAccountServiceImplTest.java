package lv.javaguru.java2.services.useraccount;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.database.jdbc.UserAccountDAOImpl;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.domain.UserAccountState;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by user on 07.04.2017.
 */
public class EditUserAccountServiceImplTest {

    private UserAccountDAO userAccountDAO = new UserAccountDAOImpl();
    EditUserAccountService service = new EditUserAccountServiceImpl();

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfUserNotFoundTest() {
        service.edit(1001l, "firstname", "lastname", UserAccountState.ADMIN);
    }

    @Test
    public void editUserAccountTest() {
        UserAccount account = userAccountDAO.getAll().stream().findFirst().get();
        service.edit(account.getUserAccountId(), "Sam", "Samson", UserAccountState.VISITOR);
        Optional <UserAccount> changedAccount = userAccountDAO.getById(account.getUserAccountId());
        assertTrue(changedAccount.isPresent());
        assertEquals(account.getUserAccountId(), changedAccount.get().getUserAccountId());
        assertEquals("Sam", changedAccount.get().getFirstName());
        assertEquals("Samson", changedAccount.get().getLastName());
        assertEquals(UserAccountState.VISITOR, changedAccount.get().getState());
    }
}