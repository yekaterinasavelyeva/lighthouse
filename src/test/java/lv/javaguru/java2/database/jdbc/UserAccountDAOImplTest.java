package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.domain.UserAccountState;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.Optional;

import static lv.javaguru.java2.domain.UserAccountBuilder.createUserAccount;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by user on 22.03.2017.
 */
@WebAppConfiguration
public class UserAccountDAOImplTest extends DBUnitTestCase {

    @Autowired
    @Qualifier("HibernateUserAccountDAO")
    private UserAccountDAO userAccountDAO ;

    @Override
    protected String getDatabaseFile() {
        return "dbscripts/UserAccountDAOImplTest.xml";
    }

    @Test
    @Transactional
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
    @Transactional
    public void testDelete() throws Exception {
        Long userId = userAccountDAO.getAll().stream().findFirst().get().getUserAccountId();
        userAccountDAO.delete(userId);
        Optional<UserAccount> userAccountFromDB = userAccountDAO.getById(userId);
        assertFalse(userAccountFromDB.isPresent());
    }

    @Test
    @Transactional
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
