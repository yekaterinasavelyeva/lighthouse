package lv.javaguru.java2.services.useraccount;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.domain.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by user on 07.04.2017.
 */
@Component
public class GetAllAccountsServiceImpl implements GetAllAccountsService {

    @Autowired
    @Qualifier("HibernateUserAccountDAO")
    private UserAccountDAO userAccountDAO;

    @Override
    public List<UserAccount> getAllAccountsList(){
        List<UserAccount> accounts = userAccountDAO.getAll();
        return accounts;
    }

    public void printMessage(String message){
        System.out.println(message);
    }
}
