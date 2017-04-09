package lv.javaguru.java2.services.reservation;


import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.database.jdbc.ReservationDAOImpl;
import lv.javaguru.java2.domain.Reservation;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by user on 09.04.2017.
 */
public class ReservationFactoryImplTest {

    ReservationFactory factory = new ReservationFactoryImpl();
    private ReservationDAO reservationDAO = new ReservationDAOImpl();

    @Test
    public void createReservationTest() {
        Reservation reservation = factory.create(LocalDate.now(), LocalDate.parse("2017-04-19"), 12345l);
        Optional<Reservation> reservationFromDB = reservationDAO.getByID(reservation.getReservationID());
        assertTrue(reservationFromDB.isPresent());
        assertEquals(reservation.getReservationID(), reservationFromDB.get().getReservationID());
        assertEquals(reservation.getDateFrom(), reservationFromDB.get().getDateFrom());
        assertEquals(reservation.getDateTo(), reservationFromDB.get().getDateTo());
        assertEquals(reservation.getLibTicketID(), reservationFromDB.get().getLibTicketID());
    }

}