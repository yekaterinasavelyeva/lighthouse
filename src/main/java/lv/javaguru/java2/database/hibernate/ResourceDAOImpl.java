package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.domain.Resource;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by user on 20.05.2017.
 */
@Component("HibernateResourceDAO")
@Transactional
public class ResourceDAOImpl implements ResourceDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Resource save(Resource resource) throws DBException {
        sessionFactory.getCurrentSession().save(resource);
        return resource;
    }

    @Override
    public Optional<Resource> getByID(Long id) throws DBException {
        Resource resource =  (Resource) sessionFactory.getCurrentSession()
                .createCriteria(Resource.class)
                .add(Restrictions.eq("resourceID", id)).uniqueResult();
        return Optional.ofNullable(resource);
    }

    @Override
    public void delete(Long id) throws DBException {
        Resource resource = (Resource) sessionFactory.getCurrentSession().load(Resource.class, id);
        sessionFactory.getCurrentSession().delete(resource);
        return;
    }

    @Override
    public void update (Resource resource) throws DBException {
        sessionFactory.getCurrentSession().update(resource);
        return;
    }

    @Override
    public List<Resource> getAll() throws DBException {
        return sessionFactory.getCurrentSession().createQuery("from Resource").list();
    }

}
