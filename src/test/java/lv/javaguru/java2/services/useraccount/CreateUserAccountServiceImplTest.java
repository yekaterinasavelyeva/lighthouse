package lv.javaguru.java2.services.useraccount;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.domain.UserAccountState;
import lv.javaguru.java2.services.useraccount.validate.UserAccountValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
public class CreateUserAccountServiceImplTest {

    @InjectMocks
    private CreateUserAccountService service = new CreateUserAccountServiceImpl();
    @Mock
    private UserAccountDAO userAccountDAO;
    @Mock
    private UserAccountValidator validator;

    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";

    @Test
    public void shouldBePossibilityToProvideUserAccountDetails() {
        service = mock(CreateUserAccountService.class);
        service.create(FIRSTNAME, LASTNAME, UserAccountState.ADMIN);
        verify(service).create(FIRSTNAME, LASTNAME, UserAccountState.ADMIN);
    }

    @Test
    public void checkAccountCreationMethodsOrder() {
        service.create(FIRSTNAME, LASTNAME, UserAccountState.ADMIN);
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
        account.setAccountId(1234l);

        when(userAccountDAO.getById(any(Long.class))).thenReturn(Optional.of(account));
        when(userAccountDAO.save(any(UserAccount.class))).thenReturn(account);
        UserAccount newAccount = service.create(FIRSTNAME,LASTNAME, UserAccountState.ADMIN);
        Optional <UserAccount> accountFromDB = userAccountDAO.getById(1234l);

        assertTrue(accountFromDB.isPresent());
        assertEquals(newAccount.getAccountId(), accountFromDB.get().getAccountId());
        assertEquals(newAccount.getFirstName(), accountFromDB.get().getFirstName());
        assertEquals(newAccount.getLastName(), accountFromDB.get().getLastName());
        assertEquals(newAccount.getState(), accountFromDB.get().getState());
    }
}