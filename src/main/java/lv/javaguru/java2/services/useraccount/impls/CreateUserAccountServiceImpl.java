package lv.javaguru.java2.services.useraccount.impls;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.domain.UserAccountState;
import lv.javaguru.java2.services.useraccount.CreateUserAccountService;
import lv.javaguru.java2.services.validators.UserAccountPropertyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static lv.javaguru.java2.domain.UserAccountBuilder.createUserAccount;

/**
 * Created by user on 26.03.2017.
 */
@Component
class CreateUserAccountServiceImpl implements CreateUserAccountService {

    @Autowired
    private UserAccountPropertyValidator validator;

    @Autowired
    @Qualifier("HibernateUserAccountDAO")
    private UserAccountDAO userAccountDAO;

    @Override
    public UserAccount create(String firstName, String lastName, UserAccountState state) {
        validator.validate(firstName, lastName, state);

        UserAccount account = createUserAccount()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withStatus(state).build();

        return userAccountDAO.save(account);
    }

}
