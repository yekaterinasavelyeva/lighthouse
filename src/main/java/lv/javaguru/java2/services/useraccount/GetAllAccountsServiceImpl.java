package lv.javaguru.java2.services.useraccount;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.database.jdbc.UserAccountDAOImpl;
import lv.javaguru.java2.domain.UserAccount;

import java.util.List;

/**
 * Created by user on 07.04.2017.
 */
public class GetAllAccountsServiceImpl implements GetAllAccountsService {

    private UserAccountDAO userAccountDAO = new UserAccountDAOImpl();

    public GetAllAccountsServiceImpl(UserAccountDAO dao){
        userAccountDAO = dao;
    }

    @Override
    public List<UserAccount> getAllAccountsList(){
        List<UserAccount> accounts = userAccountDAO.getAll();
        return accounts;
    }

    public void printMessage(String message){
        System.out.println(message);
    }
}
