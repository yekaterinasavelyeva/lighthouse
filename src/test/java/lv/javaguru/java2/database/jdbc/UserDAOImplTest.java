package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.junit.Test;

import java.util.Optional;

import static lv.javaguru.java2.domain.UserBuilder.createUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserDAOImplTest extends DBUnitTestCase {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected String getDatabaseFile() {
        return "dbscripts/UserDAOImplTest.xml";
    }

    @Test
    public void testCreate() throws Exception {
        User user = createUser()
                .withFirstName("F")
                .withLastName("L").build();

        userDAO.save(user);

        Optional<User> userFromDB = userDAO.getById(user.getUserId());
        assertTrue(userFromDB.isPresent());
        assertEquals(user.getUserId(), userFromDB.get().getUserId());
        assertEquals(user.getFirstName(), userFromDB.get().getFirstName());
        assertEquals(user.getLastName(), userFromDB.get().getLastName());
    }

}
