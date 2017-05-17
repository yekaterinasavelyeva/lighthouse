package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.database.jdbc.DAOImpl;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.UserAccount;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created by user on 14.05.2017.
 */

@Component("HibernateUserAccountDAO")
@Transactional
public class UserAccountDAOImpl extends DAOImpl implements UserAccountDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public UserAccount save(UserAccount account) throws DBException {
        sessionFactory.getCurrentSession().save(account);
        return account;
    }

    @Override
    public Optional<UserAccount> getById(Long id) throws DBException {
        UserAccount account =  (UserAccount) sessionFactory.getCurrentSession()
                .createCriteria(UserAccount.class)
                .add(Restrictions.eq("AccountID", id))
                .list();
        return Optional.ofNullable(account);
    }

    @Override
    public List<UserAccount> getAll() throws DBException {
        return sessionFactory.getCurrentSession().createQuery("from UserAccount").list();
    }

    @Override
    public void delete(Long id) throws DBException {
        UserAccount account = (UserAccount) sessionFactory.getCurrentSession().load(UserAccount.class, id);
        sessionFactory.getCurrentSession().delete(account);
        return;
    }

    @Override
    public void update(UserAccount account) throws DBException {
        sessionFactory.getCurrentSession().update(account);
        return;
    }

}
