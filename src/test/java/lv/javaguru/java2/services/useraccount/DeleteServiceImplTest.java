package lv.javaguru.java2.services.useraccount;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.services.useraccount.validate.UserAccountIdValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by user on 07.04.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class DeleteServiceImplTest {

    private DeleteService service;
    private UserAccountDAO userAccountDAO;
    private ReservationDAO reservationDAO;
    private UserAccountIdValidator deleteValidator;
    private UserAccount account = new UserAccount();

    private static final Long userAccountId = 1234l;

    @Before
    public void init() {
        deleteValidator = mock(UserAccountIdValidator.class);
        userAccountDAO = mock(UserAccountDAO.class);
        reservationDAO = mock(ReservationDAO.class);
        service = new DeleteServiceImpl(deleteValidator, userAccountDAO, reservationDAO);
        account.setUserAccountId(userAccountId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfUserNotFoundTest(){
        service.deleteUserAccount(1001l);
    }

    @Test
    public void shouldBePossibilityToProvideUserAccountID() {
        service = mock(DeleteService.class);
        service.deleteUserAccount(userAccountId);
        verify(service).deleteUserAccount(userAccountId);
    }

    @Test
    public void checkAccountDeletionMethodsOrder() {
        InOrder inOrder = Mockito.inOrder(deleteValidator, userAccountDAO, reservationDAO);
        when(userAccountDAO.getById(userAccountId)).thenReturn(Optional.of(account));
        service.deleteUserAccount(userAccountId);
        inOrder.verify(deleteValidator).validate(userAccountId);
        inOrder.verify(userAccountDAO).delete(userAccountId);
    }

    @Test
    public void deleteUserFromDatabaseTest(){
        when(userAccountDAO.getById(userAccountId)).thenReturn(Optional.of(account));
        when(userAccountDAO.getAll()).thenReturn(Arrays.asList(new UserAccount(), new UserAccount()));
        //Optional <UserAccount> accountsFromDB = userAccountDAO.getAll().stream().findFirst();
        service.deleteUserAccount(account.getUserAccountId());
        List<UserAccount> accounts = userAccountDAO.getAll();
        assertFalse(accounts.contains(account));
    }

}