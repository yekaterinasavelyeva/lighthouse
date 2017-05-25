package lv.javaguru.java2.services.useraccount.impls;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.services.useraccount.DeleteUserAccountService;
import lv.javaguru.java2.services.validators.UserAccountDeleteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 * Created by user on 05.04.2017.
 */
@Component
class DeleteUserAccountServiceImpl implements DeleteUserAccountService {

    @Autowired
    @Qualifier("HibernateUserAccountDAO")
    private UserAccountDAO userAccountDAO;

    @Autowired
    private UserAccountDeleteValidator validator;

    @Override
    public void deleteUserAccount(Long userAccountId){
        validator.validate(userAccountId);
        userAccountDAO.delete(userAccountId);
        System.out.println("User account with id " + userAccountId + " was deleted.");
    }
}
