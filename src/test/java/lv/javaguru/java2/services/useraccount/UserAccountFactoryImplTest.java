package lv.javaguru.java2.services.useraccount;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.domain.UserAccountState;
import lv.javaguru.java2.services.useraccount.validate.UserAccountValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by user on 26.03.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserAccountFactoryImplTest {

    private UserAccountFactory factory;
    private UserAccountDAO userAccountDAO;
    private UserAccountValidator validator;

    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";

    @Before
    public void init() {
        validator = mock(UserAccountValidator.class);
        userAccountDAO = mock(UserAccountDAO.class);
        factory = new UserAccountFactoryImpl(validator, userAccountDAO);
    }

    @Test
    public void shouldBePossibilityToProvideUserAccountDetails() {
        factory = mock(UserAccountFactory.class);
        factory.create(FIRSTNAME, LASTNAME, UserAccountState.ADMIN);
        verify(factory).create(FIRSTNAME, LASTNAME, UserAccountState.ADMIN);
    }

    @Test
    public void checkAccountCreationMethodsOrder() {
        factory.create(FIRSTNAME, LASTNAME, UserAccountState.ADMIN);
        InOrder inOrder = Mockito.inOrder(validator, userAccountDAO);
        inOrder.verify(validator).validate(FIRSTNAME, LASTNAME, UserAccountState.ADMIN);
        inOrder.verify(userAccountDAO).save(any(UserAccount.class));
    }

    @Test
    public void createUserAccountTest() {
        UserAccount account = new UserAccount();
        account.setLastName(LASTNAME);
        account.setFirstName(FIRSTNAME);
        account.setState(UserAccountState.ADMIN);
        account.setUserAccountId(1234l);

        when(userAccountDAO.getById(any(Long.class))).thenReturn(Optional.of(account));
        Optional <UserAccount> accountFromDB = userAccountDAO.getById(1234l);

        assertTrue(accountFromDB.isPresent());
        assertEquals(account.getUserAccountId(), accountFromDB.get().getUserAccountId());
        assertEquals(account.getFirstName(), accountFromDB.get().getFirstName());
        assertEquals(account.getLastName(), accountFromDB.get().getLastName());
        assertEquals(account.getState(), accountFromDB.get().getState());

    }


}