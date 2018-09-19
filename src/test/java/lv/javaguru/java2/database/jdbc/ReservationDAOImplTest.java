package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.database.UserAccountDAO;
import lv.javaguru.java2.domain.*;
import lv.javaguru.java2.servlet.mvc.SpringAppConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;
import static lv.javaguru.java2.domain.ReservationBuilder.createReservation;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;

/**
 * Created by user on 12/04/17.
 */
@Transactional
@Component
public class ReservationDAOImplTest  extends DBUnitTestCase {

    @Autowired
    @Qualifier("HibernateUserAccountDAO")
    private UserAccountDAO accountDAO = new UserAccountDAOImpl();

    @Autowired
    @Qualifier("HibernateResourceDAO")
    private ResourceDAO resourceDAO = new ResourceDAOImpl();

    @Autowired
    @Qualifier("HibernateReservationDAO")
    private ReservationDAO reservationDAO;

    private Reservation reservation;
    private Reservation newReservation;
    private Optional<Reservation> reservationFromDatabase;
    private UserAccount account;
    private Resource resource;


    @Override
    protected String getDatabaseFile() {
        return "dbscripts/ReservationDAOImplTest.xml";
    }

    @Override
    protected String getSchemaName(){return "java2_test";}

    @Before
    public void init() throws Exception{
       // super.init();
        ApplicationContext springContext =
                new AnnotationConfigApplicationContext(SpringAppConfig.class);
        accountDAO = springContext.getBean(UserAccountDAO.class);
        resourceDAO = springContext.getBean(ResourceDAO.class);
        reservationDAO = springContext.getBean(ReservationDAO.class);
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
        setAccountAndResource();
        createNewReservation();
        saveReservationToDatabase();
        getReservationFromDatabase();
        getReservationFromDatabaseByAccountID(account.getAccountId());
        Reservation a = reservation;
        Reservation b = newReservation;
        assertEquals(a.getDateFrom(), b.getDateFrom());
        assertEquals(a.getDateTo(), b.getDateTo());
        assertEquals(a.getAccountID(), b.getAccountID());
        assertEquals(a.getReservationID(), b.getReservationID());
        assertEquals(a.getResourceID(), b.getResourceID());
        assertEquals(a.getStatus(), b.getStatus());
    }

    @Test
    public void testGetByResourceID() throws DBException {
        setAccountAndResource();
        createNewReservation();
        saveReservationToDatabase();
        getReservationFromDatabase();
        getReservationFromDatabaseByResourceID(resource.getResourceId());
        assertThatReservationWasSavedWithCorrectProperties();
        Reservation a = reservation;
        Reservation b = newReservation;
        assertEquals(a.getDateFrom(), b.getDateFrom());
        assertEquals(a.getDateTo(), b.getDateTo());
        assertEquals(a.getAccountID(), b.getAccountID());
        assertEquals(a.getReservationID(), b.getReservationID());
        assertEquals(a.getResourceID(), b.getResourceID());
        assertEquals(a.getStatus(), b.getStatus());
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
                .filter(reservation1 -> reservation1.getReservationID().equals(reservation.getReservationID()))
                .collect(toList()).get(0);
    }

    private void getReservationFromDatabaseByResourceID(Long resourceID) {
        newReservation = reservationDAO
                .getByResourceID(resourceID)
                .stream()
                .filter(reservation1 -> reservation1.getReservationID().equals(reservation.getReservationID()))
                .collect(toList()).get(0);
    }

    private void createNewReservation() {
        reservation = createReservation()
                .withDateFrom(LocalDate.parse("2017-03-30"))
                .withDateTo(LocalDate.parse("2017-04-04"))
                .withAccountID(account.getAccountId())
                .withResourceID(resource.getResourceId())
                .withStatus(ReservationStatus.OPEN)
                .build();
    }

    private void updateReservationProperties() {
        Long thisAccountId = reservation.getAccountID();
        Long thisResourceId = reservation.getResourceID();
        reservation.setDateFrom(LocalDate.parse("2018-12-03"));
        reservation.setDateTo(LocalDate.parse("2018-12-17"));
        reservation.setAccountID(thisAccountId);
        reservation.setResourceID(thisResourceId);
        reservation.setStatus(ReservationStatus.CLOSED);
    }

    private void setAccountAndResource() {
        account = accountDAO.getAll().stream().findFirst().get();
        resource = resourceDAO.getAll().stream().findFirst().get();
    }

}