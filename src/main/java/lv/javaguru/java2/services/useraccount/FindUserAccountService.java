package lv.javaguru.java2.services.useraccount;

import lv.javaguru.java2.domain.UserAccount;

/**
 * Created by user on 07.04.2017.
 */
public interface FindUserAccountService {
    UserAccount getUserAccount(Long accountId);
}
