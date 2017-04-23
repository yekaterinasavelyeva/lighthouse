package lv.javaguru.java2.services.reservation;

import com.sun.org.apache.regexp.internal.RE;
import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.resource.validate.ResourceIdValidator;
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
public class FindByResourceIdServiceImplTest {

    private FindByResourceIdService service;
    private ReservationDAO reservationDAO;
    private ResourceIdValidator idValidator;
    private List<Reservation> reservations = new ArrayList<Reservation>();

    private static final Long RESOURCE_ID = 1234l;

    @Before
    public void init() {
        idValidator = mock(ResourceIdValidator.class);
        reservationDAO = mock(ReservationDAO.class);
        service = new FindByResourceIdServiceImpl(idValidator, reservationDAO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfReservationsNotFoundTest() {
        service.find(RESOURCE_ID);
    }

    @Test
    public void shouldBePossibilityToProvideReservationDetailsForSearch() {
        service = mock(FindByResourceIdService.class);
        service.find(RESOURCE_ID);
        verify(service).find(RESOURCE_ID);
    }

    @Test
    public void checkReservationSearchMethodsOrder() {
        reservations.add(new Reservation());
        when(reservationDAO.getByResourceID(any(Long.class))).thenReturn(reservations);
        service.find(RESOURCE_ID);
        InOrder inOrder = Mockito.inOrder(idValidator, reservationDAO);
        inOrder.verify(idValidator).validate(RESOURCE_ID);
        inOrder.verify(reservationDAO).getByResourceID(any(Long.class));
    }

    @Test
    public void searchReservationsTest() {
        Reservation reservation = new Reservation();
        reservation.setResourceID(RESOURCE_ID);
        reservation.setReservationID(1234l);
        reservations.add(reservation);
        when(reservationDAO.getByResourceID(any(Long.class))).thenReturn(reservations);
        service.find(RESOURCE_ID);
        List <Reservation> reservationsFromDB = reservationDAO.getByResourceID(RESOURCE_ID);
        assertTrue(!reservationsFromDB.isEmpty());
        assertTrue(reservationsFromDB.size()==reservations.size());
        assertEquals(reservationsFromDB.get(0).getReservationID().longValue(), 1234l);
    }


}