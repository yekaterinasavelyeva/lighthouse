package lv.javaguru.java2.services.reservation.validate.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.services.reservation.validate.ReservationRuleValidator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by vbarbasins on 2017.04.28..
 */
@RunWith(MockitoJUnitRunner.class)
public class ReservationRuleValidatorImplTest {
    private final static Long EXAMPLE_RESOURCE_ID = 1234L;
    private List<Reservation> reservations;
    private Reservation reservation;

    @Mock
    ReservationDAO reservationDAO;
    @InjectMocks
    private ReservationRuleValidator validator = new ReservationRuleValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        reservations = new ArrayList<>();
        reservation = new Reservation();
        when(reservationDAO.getByResourceID(EXAMPLE_RESOURCE_ID))
                .thenReturn(reservations);
    }

    @Test
    public void shouldThrowExceptionIfThereAreOpenedReservationsForResourceId() {
        reservation.setStatus(ReservationStatus.OPEN);
        reservations.add(reservation);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("There are opened reservations for this resource. Cannot create new reservation.");
        validator.validateReservationForResourceMustBeClosed(EXAMPLE_RESOURCE_ID);
    }

    @Test
    public void noExceptionWhenAllReservationsAreClosedForResourceId() {
        reservation.setStatus(ReservationStatus.CLOSED);
        reservations.add(reservation);
        validator.validateReservationForResourceMustBeClosed(EXAMPLE_RESOURCE_ID);
    }

}
