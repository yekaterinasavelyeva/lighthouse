package lv.javaguru.java2.services.reservation;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.services.reservation.validate.ReservationEndDateValidator;
import lv.javaguru.java2.services.reservation.validate.ReservationIdValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by user on 18.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProlongReservationServiceImplTest {

    private ProlongReservationService service;
    private ReservationDAO reservationDAO;
    private ReservationEndDateValidator dateToValidator;
    private ReservationIdValidator validator;
    private Reservation reservation = new Reservation();

    private static final LocalDate DATETO = LocalDate.now().plusDays(14l);
    private static final Long RESERVATION_ID = 1234l;

    @Before
    public void init() {
        validator = mock(ReservationIdValidator.class);
        dateToValidator = mock(ReservationEndDateValidator.class);
        reservationDAO = mock(ReservationDAO.class);
        service = new ProlongReservationServiceImpl(validator, dateToValidator, reservationDAO);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfReservationNotFoundTest() {
        service.prolong(DATETO, RESERVATION_ID);
    }

    @Test
    public void shouldBePossibilityToProvideReservationDetailsForProlongation() {
        service = mock(ProlongReservationService.class);
        service.prolong(DATETO, RESERVATION_ID);
        verify(service).prolong(DATETO, RESERVATION_ID);
    }

    @Test
    public void checkReservationProlongationMethodsOrder() {
        when(reservationDAO.getByID(RESERVATION_ID)).thenReturn(Optional.of(reservation));
        service.prolong(DATETO, RESERVATION_ID);
        InOrder inOrder = Mockito.inOrder(validator, dateToValidator, reservationDAO);
        inOrder.verify(validator).validate(RESERVATION_ID);
        inOrder.verify(dateToValidator).validate(DATETO);
        inOrder.verify(reservationDAO).update(any(Reservation.class));
    }

    @Test
    public void prolongReservationTest() {
        reservation.setReservationID(RESERVATION_ID);
        when(reservationDAO.getByID(any(Long.class))).thenReturn(Optional.of(reservation));
        service.prolong(DATETO, RESERVATION_ID);
        Optional <Reservation> prolongedReservation = reservationDAO.getByID(RESERVATION_ID);
        assertTrue(prolongedReservation.isPresent());
        assertEquals(reservation.getReservationID(), prolongedReservation.get().getReservationID());
        assertEquals(reservation.getDateTo(), prolongedReservation.get().getDateTo());
    }

}