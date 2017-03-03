package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    User save(User user);

    Optional<User> getById(Long id);

    void delete(Long id);

    void update(User user);

    List<User> getAll();

}
