package lv.javaguru.java2.services.useraccount.impls;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.services.useraccount.DeleteUserAccountService;
import lv.javaguru.java2.services.validators.UserAccountDeleteValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

/**
 * Created by user on 07.04.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class DeleteUserAccountServiceImplTest {

    private static final Long EXAMPLE_ID = 1234L;

    @Mock private UserAccountDAO userAccountDAO;
    @Mock private UserAccountDeleteValidator validator;
    @InjectMocks
    private DeleteUserAccountService service = new DeleteUserAccountServiceImpl();

    @Test
    public void checkAccountDeletionMethodsOrder() {
        InOrder inOrder = Mockito.inOrder(validator, userAccountDAO);
        service.deleteUserAccount(EXAMPLE_ID);
        inOrder.verify(validator).validate(EXAMPLE_ID);
        inOrder.verify(userAccountDAO).delete(EXAMPLE_ID);
    }
}