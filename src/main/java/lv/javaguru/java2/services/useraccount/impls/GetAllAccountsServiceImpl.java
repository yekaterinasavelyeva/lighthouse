package lv.javaguru.java2.services.useraccount.impls;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.services.useraccount.GetAllAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by user on 07.04.2017.
 */
@Component
class GetAllAccountsServiceImpl implements GetAllAccountsService {

    @Autowired
    @Qualifier("HibernateUserAccountDAO")
    private UserAccountDAO userAccountDAO;

    @Override
    public List<UserAccount> getAllAccountsList(){
        return userAccountDAO.getAll();
    }

    public void printMessage(String message){
        System.out.println(message);
    }

    //// TODO: 2017.05.25. update this class (should be any validators?)
}
