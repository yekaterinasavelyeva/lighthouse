package lv.javaguru.java2.services.reservation;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.useraccount.validate.UserAccountIdValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by user on 23.04.2017.
 */
public class FindByAccountIdServiceImplTest {

    private FindByAccountIdService service;
    private ReservationDAO reservationDAO;
    private UserAccountIdValidator idValidator;
    private List <Reservation> reservations = new ArrayList<Reservation>();

    private static final Long ACCOUNT_ID = 1234l;

    @Before
    public void init() {
        idValidator = mock(UserAccountIdValidator.class);
        reservationDAO = mock(ReservationDAO.class);
        service = new FindByAccountIdServiceImpl(idValidator, reservationDAO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfReservationsNotFoundTest() {
        service.find(ACCOUNT_ID);
    }

    @Test
    public void shouldBePossibilityToProvideReservationDetailsForSearch() {
        service = mock(FindByAccountIdService.class);
        service.find(ACCOUNT_ID);
        verify(service).find(ACCOUNT_ID);
    }

    @Test
    public void checkReservationSearchMethodsOrder() {
        reservations.add(new Reservation());
        when(reservationDAO.getByAccountID(any(Long.class))).thenReturn(reservations);
        service.find(ACCOUNT_ID);
        InOrder inOrder = Mockito.inOrder(idValidator, reservationDAO);
        inOrder.verify(idValidator).validate(ACCOUNT_ID);
        inOrder.verify(reservationDAO).getByAccountID(any(Long.class));
    }

    @Test
    public void searchReservationsTest() {
        Reservation reservation = new Reservation();
        reservation.setAccountID(ACCOUNT_ID);
        reservation.setReservationID(1234l);
        reservations.add(reservation);
        when(reservationDAO.getByAccountID(any(Long.class))).thenReturn(reservations);
        service.find(ACCOUNT_ID);
        List <Reservation> reservationsFromDB = reservationDAO.getByAccountID(ACCOUNT_ID);
        assertTrue(!reservationsFromDB.isEmpty());
        assertTrue(reservationsFromDB.size()==reservations.size());
        assertEquals(reservationsFromDB.get(0).getReservationID().longValue(), 1234l);
    }

}