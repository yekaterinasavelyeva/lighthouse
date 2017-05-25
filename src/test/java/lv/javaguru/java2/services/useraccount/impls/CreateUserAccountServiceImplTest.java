package lv.javaguru.java2.services.useraccount.impls;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.domain.UserAccountState;
import lv.javaguru.java2.services.useraccount.CreateUserAccountService;
import lv.javaguru.java2.services.validators.UserAccountPropertyValidator;
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

    private static final String FIRSTNAME = "first name";
    private static final String LASTNAME = "last name";
    private static final UserAccountState STATE = UserAccountState.VISITOR;

    @Mock private UserAccountDAO userAccountDAO;
    @Mock private UserAccountPropertyValidator validator;
    @InjectMocks
    private CreateUserAccountService service = new CreateUserAccountServiceImpl();

    @Test
    public void checkServiceMethodsOrder() {
        service.create(FIRSTNAME, LASTNAME, STATE);
        InOrder inOrder = Mockito.inOrder(validator, userAccountDAO);
        inOrder.verify(validator).validate(FIRSTNAME, LASTNAME, STATE);
        inOrder.verify(userAccountDAO).save(any(UserAccount.class));
    }
}