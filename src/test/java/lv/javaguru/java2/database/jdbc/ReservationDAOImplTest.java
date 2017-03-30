package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.ReservationStatus;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;


import static lv.javaguru.java2.domain.ReservationBuilder.createReservation;
import static org.junit.Assert.*;

/**
 * Created by VBarbasins on 30/03/17.
 */
public class ReservationDAOImplTest  extends DBUnitTestCase {

    private Reservation reservation;
    private ReservationDAO reservationDAO = new ReservationDAOImpl();
    private Optional<Reservation> reservationFromDatabase;

    @Override
    protected String getDatabaseFile() {
        return "dbscripts/ReservationDAOImplTest.xml";
    }

    @Test
    public void testSave() throws DBException {
        createNewReservation();
        saveReservationToDatabase();
        getReservationFromDatabase();
        assertThatReservationGottenFromDatabase();
        assertThatReservationWasSavedWithCorrectProperties();
    }

    @Test
    public void testUpdate() throws DBException {
        getFirstReservationFromDatabase();
        updateReservationProperties();
        updateReservationInDatabase();
        getReservationFromDatabase();
        assertThatReservationGottenFromDatabase();
        assertThatReservationWasSavedWithCorrectProperties();
    }

    @Test
    public void testGetByLibTicketID() throws DBException {
        createNewReservationWithLibTicketID(new Long("55"));
        saveReservationToDatabase();
        getReservationFromDatabaseByLibTicketID(new Long("55"));
        assertThatReservationGottenFromDatabase();
        assertThatReservationWasSavedWithCorrectProperties();
    }

    private void assertThatReservationGottenFromDatabase() {
        assertTrue(reservationFromDatabase.isPresent());
    }

    private void assertThatReservationWasSavedWithCorrectProperties() {
        Reservation a = reservation;
        Reservation b = reservationFromDatabase.get();
        assertEquals(a.getDateFrom(), b.getDateFrom());
        assertEquals(a.getDateTo(), b.getDateTo());
        assertEquals(a.getLibTicketID(), b.getLibTicketID());
        assertEquals(a.getReservationID(), b.getReservationID());
        assertEquals(a.getStatus(), b.getStatus());
    }

    private void saveReservationToDatabase() {
        reservationDAO.save(reservation);
    }

    private void updateReservationInDatabase() {
        reservationDAO.update(reservation);
    }

    private void getReservationFromDatabase() {
        reservationFromDatabase = reservationDAO.getByID(reservation.getReservationID());
    }

    private void getFirstReservationFromDatabase() {
        reservation = reservationDAO
                .getAll()
                .stream()
                .findFirst()
                .get();
    }

    private void getReservationFromDatabaseByLibTicketID(Long libTicketID) {
        reservationFromDatabase = reservationDAO
                .getByLibTicketID(libTicketID)
                .stream()
                .findFirst();
    }

    private void createNewReservation() {
        reservation = createReservation()
                .withDateFrom(LocalDate.parse("2017-03-30"))
                .withDateTo(LocalDate.parse("2017-04-04"))
                .withLibTicketID(new Long("5"))
                .withStatus(ReservationStatus.OPEN)
                .build();
    }

    private void updateReservationProperties() {
        reservation.setDateFrom(LocalDate.parse("2018-02-03"));
        reservation.setDateTo(LocalDate.parse("2018-02-04"));
        reservation.setLibTicketID(new Long("455678"));
        reservation.setStatus(ReservationStatus.CLOSED);
    }

    private void createNewReservationWithLibTicketID(Long libTicketID) {
        reservation = createReservation()
                .withDateFrom(LocalDate.parse("2017-03-30"))
                .withDateTo(LocalDate.parse("2017-04-04"))
                .withLibTicketID(libTicketID)
                .withStatus(ReservationStatus.OPEN)
                .build();
    }
}