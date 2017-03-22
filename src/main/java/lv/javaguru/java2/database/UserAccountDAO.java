package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.UserAccount;
import java.util.List;
import java.util.Optional;

/**
 * Created by user Jekaterina Saveljeva on 22.03.2017.
 */
public interface UserAccountDAO {

    UserAccount save(UserAccount account);

    Optional<UserAccount> getById(Long id);

    void delete(Long id);

    void update(UserAccount account);

    List<UserAccount> getAll();
}
