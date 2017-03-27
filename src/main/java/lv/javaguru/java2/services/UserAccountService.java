package lv.javaguru.java2.services;

import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.domain.UserAccountState;

import java.util.List;

/**
 * Created by user on 26.03.2017.
 */
public interface UserAccountService {

    void edit(Long accountId, String newFirstName, String newLastName, UserAccountState newState);

    void deleteUserAccount(Long accountId);

    List<UserAccount> getAllUsersList();

    UserAccount getUserAccount(Long accountId);
}
