package lv.javaguru.java2.services.useraccount;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.domain.UserAccountState;
import lv.javaguru.java2.services.useraccount.validate.UserAccountIdValidator;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by user on 07.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class EditUserAccountServiceImplTest {

    private EditUserAccountService service;
    private UserAccountDAO userAccountDAO;
    private UserAccountValidator validator;
    private UserAccountIdValidator idValidator;
    private UserAccount account = new UserAccount();

    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final Long ACCOUNTID = 1234l;

    @Before
    public void init() {
        validator = mock(UserAccountValidator.class);
        idValidator = mock(UserAccountIdValidator.class);
        userAccountDAO = mock(UserAccountDAO.class);
        service = new EditUserAccountServiceImpl(idValidator, validator, userAccountDAO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfUserNotFoundTest() {
        service.edit(1001l, "firstname", "lastname", UserAccountState.ADMIN);
    }

    @Test
    public void shouldBePossibilityToProvideUserAccountDetailsForEdit() {
        service = mock(EditUserAccountService.class);
        service.edit(1234l, FIRSTNAME, LASTNAME, UserAccountState.ADMIN);
        verify(service).edit(1234l, FIRSTNAME, LASTNAME, UserAccountState.ADMIN);
    }

    @Test
    public void checkAccountEditMethodsOrder() {
        when(userAccountDAO.getById(ACCOUNTID)).thenReturn(Optional.of(account));
        service.edit(ACCOUNTID, FIRSTNAME, LASTNAME, UserAccountState.ADMIN);
        InOrder inOrder = Mockito.inOrder(idValidator, validator, userAccountDAO);
        inOrder.verify(idValidator).validate(ACCOUNTID);
        inOrder.verify(validator).validate(FIRSTNAME, LASTNAME, UserAccountState.ADMIN);
        inOrder.verify(userAccountDAO).update(any(UserAccount.class));
    }

    @Test
    public void editUserAccountTest() {
        account.setLastName(LASTNAME);
        account.setFirstName(FIRSTNAME);
        account.setState(UserAccountState.ADMIN);
        account.setUserAccountId(1234l);
        when(userAccountDAO.getById(any(Long.class))).thenReturn(Optional.of(account));
        service.edit(account.getUserAccountId(), "Sam", "Samson", UserAccountState.VISITOR);
        Optional <UserAccount> changedAccount = userAccountDAO.getById(account.getUserAccountId());
        assertTrue(changedAccount.isPresent());
        assertEquals(account.getUserAccountId(), changedAccount.get().getUserAccountId());
        assertEquals(account.getFirstName(), changedAccount.get().getFirstName());
        assertEquals(account.getLastName(), changedAccount.get().getLastName());
        assertEquals(account.getState(), changedAccount.get().getState());
    }
}