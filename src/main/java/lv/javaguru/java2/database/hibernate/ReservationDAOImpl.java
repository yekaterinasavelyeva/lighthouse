package lv.javaguru.java2.database.hibernate;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
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

    public Reservation save(Reservation reservation){return new Reservation();}

    public Optional<Reservation> getByID(Long reservationID){return Optional.of(new Reservation());}

    public void update(Reservation reservation){}

    public List<Reservation> getByAccountID(Long accountID){return new ArrayList<Reservation>();}

    public List<Reservation> getByResourceID(Long resourceID){return new ArrayList<Reservation>();}

    public List<Reservation> getAll(){return new ArrayList<Reservation>();}
}
