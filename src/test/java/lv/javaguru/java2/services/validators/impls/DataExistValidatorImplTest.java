package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.services.validators.DataExistValidator;
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
import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 * Created by mobileqa on 27/04/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class DataExistValidatorImplTest {

    private static final Long EXAMPLE_ID = 1234l;
    private List<Reservation> reservations;
    private Reservation reservation;
    private Resource resource;

    @Mock
    ReservationDAO reservationDAO;
    @Mock
    ResourceDAO resourceDAO;

    @InjectMocks
    private DataExistValidator validator = new DataExistValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        reservations = new ArrayList<>();
        reservation = new Reservation();
        resource = new Resource();
    }

    @Test
    public void shouldThrowExceptionIfNoReservationsForResourseId() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservations were not found for resource id = " + EXAMPLE_ID);
        when(reservationDAO.getByResourceID(EXAMPLE_ID))
                .thenReturn(reservations);
        validator.validateReservationExistForResourceId(EXAMPLE_ID);
    }

    @Test
    public void noExceptionWhenThereAreReservationsForResourceId() {
        when(reservationDAO.getByResourceID(EXAMPLE_ID))
                .thenReturn(reservations);
        reservations.add(reservation);
        validator.validateReservationExistForResourceId(EXAMPLE_ID);
    }

    @Test
    public void shouldThrownExceptionWhenNoReservationsOnUserAccount() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservations were not found by account id = " + EXAMPLE_ID);
        when(reservationDAO.getByAccountID(EXAMPLE_ID))
                .thenReturn(reservations);
        validator.validateReservationExistForUserAccountId(EXAMPLE_ID);
    }

    @Test
    public void noExceptionWhenThereAreReservationsForUserAccountId() {
        when(reservationDAO.getByAccountID(EXAMPLE_ID))
                .thenReturn(reservations);
        reservations.add(reservation);
        validator.validateReservationExistForUserAccountId(EXAMPLE_ID);
    }

    @Test
    public void shouldThrowExceptionWhenReservationIdNotExist() {
        when(reservationDAO.getByID(EXAMPLE_ID))
                .thenReturn(Optional.empty());
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservation not found by id = " + EXAMPLE_ID);
        validator.validateReservationIdExist(EXAMPLE_ID);
    }

    @Test
    public void noExceptionWhenReservationIdExists() {
        when(reservationDAO.getByID(EXAMPLE_ID))
                .thenReturn(Optional.of(reservation));
        validator.validateReservationIdExist(EXAMPLE_ID);
    }

    @Test
    public void shouldThrowExceptionWhenResourceIdNotExist() {
        when(resourceDAO.getByID(EXAMPLE_ID))
                .thenReturn(Optional.empty());
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Resource not found by id = " + EXAMPLE_ID);
        validator.validateResourceIdExists(EXAMPLE_ID);
    }

    @Test
    public void noExceptionWhenResourceIdExists() {
        when(resourceDAO.getByID(EXAMPLE_ID))
                .thenReturn(Optional.of(resource));
        validator.validateResourceIdExists(EXAMPLE_ID);
    }
}