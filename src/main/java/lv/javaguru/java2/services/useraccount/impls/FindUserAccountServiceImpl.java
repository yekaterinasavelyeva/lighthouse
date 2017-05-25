package lv.javaguru.java2.services.useraccount.impls;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.services.useraccount.FindUserAccountService;
import lv.javaguru.java2.services.validators.DataExistValidator;
import lv.javaguru.java2.services.validators.DataInputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by user on 07.04.2017.
 */
@Component
class FindUserAccountServiceImpl implements FindUserAccountService {

    @Autowired
    @Qualifier("HibernateUserAccountDAO")
    private UserAccountDAO userAccountDAO;
    @Autowired
    private DataInputValidator inputValidator;
    @Autowired
    private DataExistValidator existValidator;

    @Override
    public UserAccount getUserAccount(Long userAccountId){
        inputValidator.validateUserAccountIdInput(userAccountId);
        existValidator.validateUserAccountIdExists(userAccountId);
        return userAccountDAO.getById(userAccountId).get();
    }
}
