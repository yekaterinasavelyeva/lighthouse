package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.domain.UserAccountState;
import org.junit.Test;

import java.util.Optional;

import static lv.javaguru.java2.domain.UserAccountBuilder.createUserAccount;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by user on 22.03.2017.
 */
public class UserAccountDAOImplTest extends DBUnitTestCase {
    private UserAccountDAO userAccountDAO = new UserAccountDAOImpl();

    @Override
    protected String getDatabaseFile() {
        return "dbscripts/UserAccountDAOImplTest.xml";
    }

    @Test
    public void testCreate() throws Exception {
        UserAccount account = createUserAccount()
                .withFirstName("F")
                .withLastName("L")
                .withStatus(UserAccountState.VISITOR).build();

        userAccountDAO.save(account);

        Optional<UserAccount> userAccountFromDB = userAccountDAO.getById(account.getUserAccountId());
        assertTrue(userAccountFromDB.isPresent());
        assertEquals(account.getUserAccountId(), userAccountFromDB.get().getUserAccountId());
        assertEquals(account.getFirstName(), userAccountFromDB.get().getFirstName());
        assertEquals(account.getLastName(), userAccountFromDB.get().getLastName());
        assertEquals(account.getState(), userAccountFromDB.get().getState());
    }

    @Test
    public void testDelete() throws Exception {
        Long userId = userAccountDAO.getAll().stream().findFirst().get().getUserAccountId();
        userAccountDAO.delete(userId);
        Optional<UserAccount> userAccountFromDB = userAccountDAO.getById(userId);
        assertFalse(userAccountFromDB.isPresent());
    }

    @Test
    public void testUpdate() throws Exception {
        UserAccount account = userAccountDAO.getAll().stream().findFirst().get();
        account.setFirstName("John");
        account.setLastName("Johnson");
        account.setState(UserAccountState.ADMIN);
        userAccountDAO.update(account);
        Optional<UserAccount> userAccountFromDB = userAccountDAO.getById(account.getUserAccountId());
        assertTrue(userAccountFromDB.isPresent());
        assertEquals(account.getUserAccountId(), userAccountFromDB.get().getUserAccountId());
        assertEquals(account.getFirstName(), userAccountFromDB.get().getFirstName());
        assertEquals(account.getLastName(), userAccountFromDB.get().getLastName());
        assertEquals(account.getState(), userAccountFromDB.get().getState());
    }

}
