package lv.javaguru.java2.services.useraccount;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.domain.UserAccountState;
import lv.javaguru.java2.services.useraccount.validate.UserAccountIdValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by user on 07.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class FindUserAccountServiceImplTest {

    private FindUserAccountService service;
    private UserAccountDAO userAccountDAO;
    private UserAccountIdValidator validator;
    private UserAccount account = new UserAccount();

    private static final Long userAccountId = 1234l;


    @Before
    public void init() {
        validator = mock(UserAccountIdValidator.class);
        userAccountDAO = mock(UserAccountDAO.class);
        service = new FindUserAccountServiceImpl(validator, userAccountDAO);
        account.setUserAccountId(userAccountId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfUserNotFoundTest() {
        service.getUserAccount(userAccountId);
    }

    @Test
    public void checkAccountCreationMethodsOrder() {
        when(userAccountDAO.getById(userAccountId)).thenReturn(Optional.of(account));
        service.getUserAccount(userAccountId);
        InOrder inOrder = Mockito.inOrder(validator, userAccountDAO);
        inOrder.verify(validator).validate(userAccountId);
        inOrder.verify(userAccountDAO).getById(userAccountId);
    }

    @Test
    public void getUserAccountByIDTest(){
        account.setLastName("Sam");
        account.setFirstName("Samwell");
        account.setState(UserAccountState.VISITOR);

        when(userAccountDAO.getById(any(Long.class))).thenReturn(Optional.of(account));
        when(userAccountDAO.getAll()).thenReturn(Arrays.asList(account));

        Optional<UserAccount> userAccountFromDB = userAccountDAO.getAll().stream().findFirst();
        Long accountId = userAccountFromDB.get().getUserAccountId();
        UserAccount account = service.getUserAccount(accountId);

        assertEquals(account.getFirstName(), userAccountFromDB.get().getFirstName());
        assertEquals(account.getLastName(), userAccountFromDB.get().getLastName());
        assertEquals(account.getState(), userAccountFromDB.get().getState());
    }

}