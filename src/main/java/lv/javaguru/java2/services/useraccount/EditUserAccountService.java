package lv.javaguru.java2.services.useraccount;

import lv.javaguru.java2.domain.UserAccountState;

/**
 * Created by user on 07.04.2017.
 */
public interface EditUserAccountService {

    void edit(Long accountId, String newFirstName, String newLastName, UserAccountState newState);
}
