package lv.javaguru.java2.services;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();
    private UserValidator userValidator = new UserValidatorImpl();

    @Override
    public void edit(Long userId,
                     String newFirstName,
                     String newLastName) {
        Optional<User> userOpt = userDAO.getById(userId);
        if (!userOpt.isPresent()) {
            throw new IllegalArgumentException("User not found by id = " + userId);
        }

        userValidator.validate(newFirstName, newLastName);

        User user = userOpt.get();
        user.setFirstName(newFirstName);
        user.setLastName(newLastName);
        userDAO.update(user);
    }

}
