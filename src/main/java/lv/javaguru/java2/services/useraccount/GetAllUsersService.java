package lv.javaguru.java2.services.useraccount;

import lv.javaguru.java2.domain.UserAccount;

import java.util.List;

/**
 * Created by user on 07.04.2017.
 */
public interface GetAllUsersService {
    List<UserAccount> getAllUsersList();
}
