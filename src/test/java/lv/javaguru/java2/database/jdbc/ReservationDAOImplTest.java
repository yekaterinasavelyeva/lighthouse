package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.domain.*;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;
import static lv.javaguru.java2.domain.ReservationBuilder.createReservation;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;

/**
 * Created by VBarbasins on 30/03/17.
 */


public class ReservationDAOImplTest  extends DBUnitTestCase {

 //As constraints and foreign keys are implemented, resources,
 //accounts and library tickets have to be
 //implemented before testing reservations itselfes, or mock objects have to be used.

    private UserAccountDAO accountDAO = new UserAccountDAOImpl();
    private ResourceDAO resourceDAO = new ResourceDAOImpl();
    private Reservation reservation;
    private Reservation newReservation;
    private ReservationDAO reservationDAO = new ReservationDAOImpl();
    private Optional<Reservation> reservationFromDatabase;
    private UserAccount account;
    private Resource resource;


    @Override
    protected String getDatabaseFile() {
        return "dbscripts/ReservationDAOImplTest.xml";
    }

    @Test
    public void testSave() throws DBException {
        setAccountAndResource();
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
    public void testGetByAccountID() throws DBException {

    }

    @Test
    public void testGetByResourceID() throws DBException {

    }


    private void assertThatReservationGottenFromDatabase()
    {
        assertTrue(reservationFromDatabase.isPresent());
    }


    private void assertThatReservationWasSavedWithCorrectProperties() {
        Reservation a = reservation;
        Reservation b = reservationFromDatabase.get();
        assertEquals(a.getDateFrom(), b.getDateFrom());
        assertEquals(a.getDateTo(), b.getDateTo());
        assertEquals(a.getAccountID(), b.getAccountID());
        assertEquals(a.getReservationID(), b.getReservationID());
        assertEquals(a.getResourceID(), b.getResourceID());
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

    private void getReservationFromDatabaseByAccountID(Long accountID) {
        newReservation = reservationDAO
                .getByAccountID(accountID)
                .stream()
                .filter(reservation1 -> reservation1.getReservationID()==reservation.getReservationID())
                .collect(toList()).get(0);
    }

    private void getReservationFromDatabaseByResourceID(Long resourceID) {
        newReservation = reservationDAO
                .getByResourceID(resourceID)
                .stream()
                .filter(reservation1 -> reservation1.getReservationID()==reservation.getReservationID())
                .collect(toList()).get(0);
    }

    private void createNewReservation() {
        reservation = createReservation()
                .withDateFrom(LocalDate.parse("2017-03-30"))
                .withDateTo(LocalDate.parse("2017-04-04"))
                .withAccountID(account.getUserAccountId())
                .withResourceID(resource.getResourceID())
                .withStatus(ReservationStatus.OPEN)
                .build();
    }

    private void updateReservationProperties() {
        Long thisAccountId = reservation.getAccountID();
        Long thisResourceId = reservation.getResourceID();
        reservation.setDateFrom(LocalDate.parse("2018-02-03"));
        reservation.setDateTo(LocalDate.parse("2018-02-04"));
        reservation.setAccountID(thisAccountId);
        reservation.setResourceID(thisResourceId);
        reservation.setStatus(ReservationStatus.CLOSED);
    }

    private void createNewReservationWithAccountIDAndResourceID() {

        reservation = createReservation()
                .withDateFrom(LocalDate.parse("2017-03-30"))
                .withDateTo(LocalDate.parse("2017-04-04"))
                .withAccountID(account.getUserAccountId())
                .withResourceID(resource.getResourceID())
                .withStatus(ReservationStatus.OPEN)
                .build();
    }

    private void setAccountAndResource() {
        account = accountDAO.getAll().stream().findFirst().get();
        resource = resourceDAO.getAll().stream().findFirst().get();
    }

}