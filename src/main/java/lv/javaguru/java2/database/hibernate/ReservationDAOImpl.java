package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.domain.UserAccount;
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
@Component("HibernateReservationDAO")
@Transactional
public class ReservationDAOImpl implements ReservationDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Reservation save(Reservation reservation) throws DBException {
        sessionFactory.getCurrentSession().save(reservation);
        return reservation;
    }

    @Override
    public Optional<Reservation> getByID(Long id) throws DBException {
        Reservation reservation =  (Reservation) sessionFactory.getCurrentSession()
                .createCriteria(Reservation.class)
                .add(Restrictions.eq("reservationID", id)).uniqueResult();
        return Optional.ofNullable(reservation);
    }

    @Override
    public void update(Reservation reservation) throws DBException {
        sessionFactory.getCurrentSession().update(reservation);
        return;
    }

    @Override
    public List<Reservation> getByAccountID(Long accountID) throws DBException {
        List <Reservation> reservations = sessionFactory.getCurrentSession()
                .createCriteria(Reservation.class)
                .add(Restrictions.eq("accountID", accountID)).list();
        return reservations;
    }

    @Override
    public List<Reservation> getByResourceID(Long resourceID) throws DBException {
        List <Reservation> reservations = sessionFactory.getCurrentSession()
                .createCriteria(Reservation.class)
                .add(Restrictions.eq("resourceID", resourceID)).list();
        return reservations;
    }

    @Override
    public List<Reservation> getAll() throws DBException {
        return sessionFactory.getCurrentSession().createQuery("from Reservation").list();
    }
}
