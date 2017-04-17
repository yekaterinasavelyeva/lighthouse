package lv.javaguru.java2.services.reservation;


import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.services.reservation.validate.ReservationValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by user on 09.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReservationFactoryImplTest {

    private ReservationFactory factory;
    private ReservationDAO reservationDAO;
    private ReservationValidator validator;

    private static final Long RESERVATIONID = 1234l;
    private static final LocalDate DATEFROM = LocalDate.now();
    private static final LocalDate DATETO = LocalDate.now().plusDays(14);
    private static final Long ACCOUNTID = 1234l;
    private static final Long RESOURCEID = 1234l;
    private static final ReservationStatus STATUS = ReservationStatus.OPEN;

    @Before
    public void init() {
        validator = mock(ReservationValidator.class);
        reservationDAO = mock(ReservationDAO.class);
        factory = new ReservationFactoryImpl(validator, reservationDAO);
    }

    @Test
    public void shouldBePossibilityToProvideReservationDetails() {
        factory = mock(ReservationFactory.class);
        factory.create(DATEFROM, DATETO, ACCOUNTID, RESOURCEID);
        verify(factory).create(DATEFROM, DATETO, ACCOUNTID, RESOURCEID);
    }

    @Test
    public void checkReservationCreationMethodsOrder() {
        factory.create(DATEFROM, DATETO, ACCOUNTID, RESOURCEID);
        InOrder inOrder = Mockito.inOrder(validator, reservationDAO);
        inOrder.verify(validator).validate(DATEFROM, DATETO, ACCOUNTID, RESOURCEID);
        inOrder.verify(reservationDAO).save(any(Reservation.class));
    }

    @Test
    public void createReservationTest() {
        Reservation reservation = new Reservation();
        reservation.setReservationID(RESERVATIONID);
        reservation.setDateFrom(DATEFROM);
        reservation.setDateTo(DATETO);
        reservation.setAccountID(ACCOUNTID);
        reservation.setResourceID(RESOURCEID);
        reservation.setStatus(STATUS);

        when(reservationDAO.getByID(any(Long.class))).thenReturn(Optional.of(reservation));
        when(reservationDAO.save(any(Reservation.class))).thenReturn(reservation);
        Reservation newReservation = factory.create(DATEFROM, DATETO, ACCOUNTID, RESOURCEID) ;
        Optional <Reservation> reservationFromDB = reservationDAO.getByID(RESERVATIONID);

        assertTrue(reservationFromDB.isPresent());
        assertEquals(newReservation.getDateFrom(), reservationFromDB.get().getDateFrom());
        assertEquals(newReservation.getDateTo(), reservationFromDB.get().getDateTo());
        assertEquals(newReservation.getAccountID(), reservationFromDB.get().getAccountID());
        assertEquals(newReservation.getResourceID(), reservationFromDB.get().getResourceID());
        assertEquals(newReservation.getStatus(), reservationFromDB.get().getStatus());
        assertEquals(newReservation.getReservationID(), reservationFromDB.get().getReservationID());
    }
}