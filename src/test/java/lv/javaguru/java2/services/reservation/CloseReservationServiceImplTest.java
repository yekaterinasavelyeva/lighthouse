package lv.javaguru.java2.services.reservation;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.validate.ReservationIdValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by user on 17.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class CloseReservationServiceImplTest {

    private CloseReservationService service;
    private ReservationDAO reservationDAO;
    private ReservationIdValidator validator;
    private Reservation reservation = new Reservation();

    private static final Long RESERVATIONID = 1234l;

    @Before
    public void init() {
        validator = mock(ReservationIdValidator.class);
        reservationDAO = mock(ReservationDAO.class);
        service = new CloseReservationServiceImpl(validator, reservationDAO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfReservationNotFoundTest() {
        service.close(RESERVATIONID);
    }

    @Test
    public void shouldBePossibilityToProvideReservationDetailsForClose() {
        service = mock(CloseReservationService.class);
        service.close(RESERVATIONID);
        verify(service).close(RESERVATIONID);
    }

    @Test
    public void checkReservationCloseMethodsOrder() {
        when(reservationDAO.getByID(RESERVATIONID)).thenReturn(Optional.of(reservation));
        service.close(RESERVATIONID);
        InOrder inOrder = Mockito.inOrder(validator, reservationDAO);
        inOrder.verify(validator).validate(RESERVATIONID);
        inOrder.verify(reservationDAO).update(any(Reservation.class));
    }

    @Test
    public void closeReservationTest() {
        reservation.setReservationID(RESERVATIONID);
        when(reservationDAO.getByID(any(Long.class))).thenReturn(Optional.of(reservation));
        service.close(RESERVATIONID);
        Optional <Reservation> closedReservation = reservationDAO.getByID(RESERVATIONID);
        assertTrue(closedReservation.isPresent());
        assertEquals(reservation.getReservationID(), closedReservation.get().getReservationID());
        assertEquals(reservation.getStatus(), closedReservation.get().getStatus());
    }

}