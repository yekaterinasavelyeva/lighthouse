package lv.javaguru.java2.services.useraccount.impls;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.domain.UserAccountState;
import lv.javaguru.java2.services.useraccount.EditUserAccountService;
import lv.javaguru.java2.services.validators.UserAccountEditValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by user on 07.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class EditUserAccountServiceImplTest {

    private static final Long EXAMPLE_LONG = 1234L;
    private static final String EXAMPLE_STRING = "example";
    private static final UserAccountState EXAMPLE_STATE = UserAccountState.VISITOR;

    @Mock private UserAccountDAO userAccountDAO;
    @Mock private UserAccountEditValidator validator;
    @InjectMocks
    private EditUserAccountService service = new EditUserAccountServiceImpl();

    @Test
    public void checkEditMethodsOrder() {
        UserAccount userAccount = Mockito.mock(UserAccount.class);
        when(userAccountDAO.getById(EXAMPLE_LONG))
                .thenReturn(Optional.of(userAccount));
        InOrder inOrder = Mockito.inOrder(userAccount, userAccountDAO, validator);
        service.edit(EXAMPLE_LONG, EXAMPLE_STRING, EXAMPLE_STRING, EXAMPLE_STATE);
        inOrder.verify(validator).validate(EXAMPLE_LONG, EXAMPLE_STRING, EXAMPLE_STRING, EXAMPLE_STATE);
        inOrder.verify(userAccountDAO).getById(EXAMPLE_LONG);
        inOrder.verify(userAccount).setFirstName(EXAMPLE_STRING);
        inOrder.verify(userAccount).setLastName(EXAMPLE_STRING);
        inOrder.verify(userAccount).setState(EXAMPLE_STATE);
        inOrder.verify(userAccountDAO).update(userAccount);
    }
}