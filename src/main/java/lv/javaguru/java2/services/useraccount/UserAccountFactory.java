package lv.javaguru.java2.services.useraccount;

import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.domain.UserAccountState;

/**
 * Created by user on 26.03.2017.
 */
public interface UserAccountFactory {

    UserAccount create(String firstName, String lastName, UserAccountState state);
}
