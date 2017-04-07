package lv.javaguru.java2.services.useraccount;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.database.jdbc.UserAccountDAOImpl;
import lv.javaguru.java2.domain.UserAccount;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by user on 07.04.2017.
 */
public class FindUserAccountServiceImplTest {

    private UserAccountDAO userAccountDAO = new UserAccountDAOImpl();
    FindUserAccountService service = new FindUserAccountServiceImpl();

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfUserNotFoundTest() {
        service.getUserAccount(1001l);
    }

    @Test
    public void getUserAccountByIDTest(){
        Long userAccountId = userAccountDAO.getAll().stream().findFirst().get().getUserAccountId();
        Optional<UserAccount> userAccountFromDB = userAccountDAO.getById(userAccountId);
        UserAccount account = service.getUserAccount(userAccountId);
        assertEquals(account.getUserAccountId(), userAccountFromDB.get().getUserAccountId());
        assertEquals(account.getFirstName(), userAccountFromDB.get().getFirstName());
        assertEquals(account.getLastName(), userAccountFromDB.get().getLastName());
        assertEquals(account.getState(), userAccountFromDB.get().getState());
    }

}