package lv.javaguru.java2.services.useraccount;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.database.jdbc.UserAccountDAOImpl;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.domain.UserAccountState;
import lv.javaguru.java2.services.useraccount.validate.UserAccountValidator;
import lv.javaguru.java2.services.useraccount.validate.impls.UserAccountValidatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static lv.javaguru.java2.domain.UserAccountBuilder.createUserAccount;

/**
 * Created by user on 26.03.2017.
 */
@Component
public class CreateUserAccountServiceImpl implements CreateUserAccountService {

    @Autowired
    private UserAccountValidator userAccountValidator;
    @Autowired
    private UserAccountDAO userAccountDAO;

     @Override
    public UserAccount create(String firstName, String lastName, UserAccountState state) {
        userAccountValidator.validate(firstName, lastName, state);

        UserAccount account = createUserAccount()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withStatus(state).build();

        return userAccountDAO.save(account);
    }

}
