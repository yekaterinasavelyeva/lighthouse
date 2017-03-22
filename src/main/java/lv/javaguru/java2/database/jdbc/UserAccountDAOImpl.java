package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.domain.UserAccount;
import lv.javaguru.java2.domain.UserAccountState;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Created by user Jekaterina Saveljeva on 22.03.2017.
 */
public class UserAccountDAOImpl extends DAOImpl implements UserAccountDAO {

    public UserAccount save(UserAccount account) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into ACCOUNTS values (default, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, account.getFirstName());
            preparedStatement.setString(2, account.getLastName());
            preparedStatement.setString(3, account.getState().name());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                account.setUserAccountId(rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute UserAccountDAOImpl.save()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }

        return account;
    }

    public Optional<UserAccount> getById(Long id) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from ACCOUNTS where UserID = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            UserAccount account = null;
            if (resultSet.next()) {
                account = new UserAccount();
                account.setUserAccountId(resultSet.getLong("AccountID"));
                account.setFirstName(resultSet.getString("FirstName"));
                account.setLastName(resultSet.getString("LastName"));
                account.setState(UserAccountState.valueOf(resultSet.getString("Status")));
            }
            return Optional.ofNullable(account);
        } catch (Throwable e) {
            System.out.println("Exception while execute UserAccountDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public List<UserAccount> getAll() throws DBException {
        List<UserAccount> accounts = new ArrayList<UserAccount>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from ACCOUNTS");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserAccount account = new UserAccount();
                account.setUserAccountId(resultSet.getLong("AccountID"));
                account.setFirstName(resultSet.getString("FirstName"));
                account.setLastName(resultSet.getString("LastName"));
                account.setState(UserAccountState.valueOf(resultSet.getString("Status")));
                accounts.add(account);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list UserAccountDAOImpl.getList()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return accounts;
    }

    public void delete(Long id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from ACCOUNTS where UserID = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute UserAccountDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public void update(UserAccount account) throws DBException {
        if (account == null) {
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update ACCOUNTS set FirstName = ?, LastName = ?, Status = ? " +
                            "where AccountID = ?");
            preparedStatement.setString(1, account.getFirstName());
            preparedStatement.setString(2, account.getLastName());
            preparedStatement.setString(3, account.getState().name());
            preparedStatement.setLong(4, account.getUserAccountId());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute UserAccountDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }
}
