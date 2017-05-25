package lv.javaguru.java2.services.useraccount.impls;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.services.useraccount.FindUserAccountService;
import lv.javaguru.java2.services.validators.DataExistValidator;
import lv.javaguru.java2.services.validators.DataInputValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by user on 07.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class FindUserAccountServiceImplTest {

    private static final Long EXAMPLE_LONG = 1234L;

    @Mock private UserAccountDAO userAccountDAO;
    @Mock private DataInputValidator inputValidator;
    @Mock private DataExistValidator existValidator;
    @Mock private UserAccount userAccount;
    @InjectMocks
    private FindUserAccountService service = new FindUserAccountServiceImpl();

    @Test
    public void checkFindServiceMethodsOrder() {
        when(userAccountDAO.getById(EXAMPLE_LONG))
                .thenReturn(Optional.of(userAccount));
        InOrder inOrder = Mockito.inOrder(userAccountDAO, inputValidator, existValidator);
        UserAccount fromService = service.getUserAccount(EXAMPLE_LONG);
        inOrder.verify(inputValidator).validateUserAccountIdInput(EXAMPLE_LONG);
        inOrder.verify(existValidator).validateUserAccountIdExists(EXAMPLE_LONG);
        inOrder.verify(userAccountDAO).getById(EXAMPLE_LONG);
        assertEquals(fromService, userAccount);
    }
}