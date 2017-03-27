package lv.javaguru.java2.services;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.database.jdbc.UserAccountDAOImpl;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.domain.UserAccountState;

import static lv.javaguru.java2.domain.UserAccountBuilder.createUserAccount;

/**
 * Created by user on 26.03.2017.
 */
public class UserAccountFactoryImpl implements UserAccountFactory {

    private UserAccountValidator userAccountValidator = new UserAccountValidatorImpl();
    private UserAccountDAO userAccountDAO = new UserAccountDAOImpl();


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
