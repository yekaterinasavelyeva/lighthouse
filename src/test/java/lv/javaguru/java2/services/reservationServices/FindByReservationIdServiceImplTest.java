package lv.javaguru.java2.services.reservationServices;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.services.reservationServices.validate.ReservationIdValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by user on 18.04.2017.
 */
public class FindByReservationIdServiceImplTest {

    private FindByReservationIdService service;
    private ReservationDAO reservationDAO;
    private ReservationIdValidator idValidator;
    private Reservation reservation = new Reservation();

    private static final Long RESERVATION_ID = 1234l;

    @Before
    public void init() {
        idValidator = mock(ReservationIdValidator.class);
        reservationDAO = mock(ReservationDAO.class);
        service = new FindByReservationIdServiceImpl(idValidator, reservationDAO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfReservationNotFoundTest() {
        service.find(RESERVATION_ID);
    }

    @Test
    public void shouldBePossibilityToProvideReservationDetailsForSearch() {
        service = mock(FindByReservationIdService.class);
        service.find(RESERVATION_ID);
        verify(service).find(RESERVATION_ID);
    }

    @Test
    public void checkReservationSearchMethodsOrder() {
        when(reservationDAO.getByID(RESERVATION_ID)).thenReturn(Optional.of(reservation));
        service.find(RESERVATION_ID);
        InOrder inOrder = Mockito.inOrder(idValidator, reservationDAO);
        inOrder.verify(idValidator).validate(RESERVATION_ID);
        inOrder.verify(reservationDAO).getByID(any(Long.class));
    }

    @Test
    public void searchReservationTest() {
        reservation.setReservationID(RESERVATION_ID);
        reservation.setStatus(ReservationStatus.OPEN);
        when(reservationDAO.getByID(any(Long.class))).thenReturn(Optional.of(reservation));
        service.find(RESERVATION_ID);
        Optional <Reservation> prolongedReservation = reservationDAO.getByID(RESERVATION_ID);
        assertTrue(prolongedReservation.isPresent());
        assertEquals(reservation.getReservationID(), prolongedReservation.get().getReservationID());
        assertEquals(reservation.getStatus(), prolongedReservation.get().getStatus());
    }

}