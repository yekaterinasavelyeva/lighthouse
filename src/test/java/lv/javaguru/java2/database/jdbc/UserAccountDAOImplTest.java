package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.database.hibernate.*;
import lv.javaguru.java2.database.hibernate.UserAccountDAOImpl;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.domain.UserAccountState;
import lv.javaguru.java2.servlet.mvc.SpringAppConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

import static lv.javaguru.java2.domain.UserAccountBuilder.createUserAccount;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by user on 22.03.2017.
 */

@Transactional
@Component
public class UserAccountDAOImplTest extends DBUnitTestCase {

    @Autowired
    @Qualifier("HibernateUserAccountDAO")
    private UserAccountDAO userAccountDAO = new UserAccountDAOImpl();

    @Override
    protected String getDatabaseFile() {
        return "dbscripts/UserAccountDAOImplTest.xml";
    }

   @Before
    public void init(){
        ApplicationContext springContext =
                new AnnotationConfigApplicationContext(SpringAppConfig.class);
                userAccountDAO = springContext.getBean(UserAccountDAO.class);
    }

    @Test
    public void testCreate() throws Exception {
        UserAccount account = createUserAccount()
                .withFirstName("F")
                .withLastName("L")
                .withStatus(UserAccountState.VISITOR).build();

        userAccountDAO.save(account);

        Optional<UserAccount> userAccountFromDB = userAccountDAO.getById(account.getAccountId());
        assertTrue(userAccountFromDB.isPresent());
        assertEquals(account.getAccountId(), userAccountFromDB.get().getAccountId());
        assertEquals(account.getFirstName(), userAccountFromDB.get().getFirstName());
        assertEquals(account.getLastName(), userAccountFromDB.get().getLastName());
        assertEquals(account.getState(), userAccountFromDB.get().getState());
    }

    @Test
    public void testDelete() throws Exception {
        Long userId = userAccountDAO.getAll().stream().findFirst().get().getAccountId();
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
        Optional<UserAccount> userAccountFromDB = userAccountDAO.getById(account.getAccountId());
        assertTrue(userAccountFromDB.isPresent());
        assertEquals(account.getAccountId(), userAccountFromDB.get().getAccountId());
        assertEquals(account.getFirstName(), userAccountFromDB.get().getFirstName());
        assertEquals(account.getLastName(), userAccountFromDB.get().getLastName());
        assertEquals(account.getState(), userAccountFromDB.get().getState());
    }

}
