package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.services.validators.ResourceRuleValidator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by Viktor on 2017.05.22..
 */
@RunWith(MockitoJUnitRunner.class)
public class ResourceRuleValidatorImplTest {
    private static final Long EXAMPLE_ID = 1234L;
    private static final int RELEASE_YEAR_BELOW_LIMIT = 0;
    private static final int RELEASE_YEAR_OVER_LIMIT = LocalDate.now().getYear() + 1;
    private static final int RELEASE_YEAR_MIN_POSSIBLE = 1;
    private static final int RELEASE_YEAR_MAX_POSSIBLE = LocalDate.now().getYear();
    private List<Reservation> reservations;
    private Reservation reservation;

    @Mock
    ReservationDAO reservationDAO;
    @InjectMocks
    private ResourceRuleValidator validator = new ResourceRuleValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        reservations = new ArrayList<>();
        reservation = new Reservation();
    }

    @Test
    public void shouldThrowExceptionIfReleaseYearBelowLimit() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Release Year cannot be 0 or greater than current year!");
        validator.validateReleaseYearForNewResource(RELEASE_YEAR_BELOW_LIMIT);
    }

    @Test
    public void shouldThrowExceptionIfReleaseYearOverLimit() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Release Year cannot be 0 or greater than current year!");
        validator.validateReleaseYearForNewResource(RELEASE_YEAR_OVER_LIMIT);
    }

    @Test
    public void noExceptionWhenReleaseYearMinPossible() {
        validator.validateReleaseYearForNewResource(RELEASE_YEAR_MIN_POSSIBLE);
    }

    @Test
    public void noExceptionWhenReleaseYearMaxPossible() {
        validator.validateReleaseYearForNewResource(RELEASE_YEAR_MAX_POSSIBLE);
    }

    @Test
    public void shouldThrowExceptionIfThereIsOpenReservationForResource() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Cannot delete resource. There is open reservation for it");
        reservation.setStatus(ReservationStatus.OPEN);
        reservations.add(reservation);
        when(reservationDAO.getByResourceID(EXAMPLE_ID))
                .thenReturn(reservations);
        validator.validateReservationStatusForResource(EXAMPLE_ID);
    }

    @Test
    public void noExceptionIfAllReservationsForResourceClosed() {
        reservation.setStatus(ReservationStatus.CLOSED);
        reservations.add(reservation);
        when(reservationDAO.getByResourceID(EXAMPLE_ID))
                .thenReturn(reservations);
        validator.validateReservationStatusForResource(EXAMPLE_ID);
    }

    @Test
    public void noExceptionIfThereAreNoReservationsForResource() {
        when(reservationDAO.getByResourceID(EXAMPLE_ID))
                .thenReturn(reservations);
        validator.validateReservationStatusForResource(EXAMPLE_ID);
    }
}