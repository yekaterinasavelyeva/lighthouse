package lv.javaguru.java2.services.useraccount;

import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.database.jdbc.UserAccountDAOImpl;
import lv.javaguru.java2.domain.UserAccount;

import java.util.List;

/**
 * Created by user on 07.04.2017.
 */
public class GetAllUsersServiceImpl implements GetAllUsersService {

    private UserAccountDAO userAccountDAO = new UserAccountDAOImpl();

    public GetAllUsersServiceImpl (UserAccountDAO dao){
        userAccountDAO = dao;
    }

    @Override
    public List<UserAccount> getAllUsersList(){
        List<UserAccount> accounts = userAccountDAO.getAll();
        return accounts;
    }

    public void printMessage(String message){
        System.out.println(message);
    }
}
