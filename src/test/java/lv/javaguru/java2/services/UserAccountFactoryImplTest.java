package lv.javaguru.java2.services;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.database.jdbc.UserAccountDAOImpl;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.domain.UserAccountState;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by user on 26.03.2017.
 */
public class UserAccountFactoryImplTest {

    UserAccountFactory factory = new UserAccountFactoryImpl();
    private UserAccountDAO userAccountDAO = new UserAccountDAOImpl();

    @Test
    public void createUserAccountTest() {
        UserAccount account = factory.create("firstname", "lastname", UserAccountState.ADMIN);
        Optional<UserAccount> userAccountFromDB = userAccountDAO.getById(account.getUserAccountId());
        assertTrue(userAccountFromDB.isPresent());
        assertEquals(account.getUserAccountId(), userAccountFromDB.get().getUserAccountId());
        assertEquals(account.getFirstName(), userAccountFromDB.get().getFirstName());
        assertEquals(account.getLastName(), userAccountFromDB.get().getLastName());
        assertEquals(account.getState(), userAccountFromDB.get().getState());
    }


}