package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.services.validators.ReservationRuleValidator;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by vbarbasins on 2017.04.28..
 */
@RunWith(MockitoJUnitRunner.class)
public class ReservationRuleValidatorImplTest {
    private final static Long EXAMPLE_RESOURCE_ID = 1234L;
    private static final LocalDate START_DATE_TODAY = LocalDate.now();
    private static final LocalDate START_DATE_YESTERDAY = LocalDate.now().minusDays(1);
    private static final LocalDate START_DATE_TOMORROW = LocalDate.now().plusDays(1);
    private static final LocalDate END_DATE_TOO_EARLY = LocalDate.now().plusDays(6);
    private static final LocalDate END_DATE_MIN_POSSIBLE = LocalDate.now().plusDays(7);
    private static final LocalDate END_DATE_TOO_LATE = LocalDate.now().plusDays(31);
    private static final LocalDate END_DATE_MAX_POSSIBLE = LocalDate.now().plusDays(30);
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
        validator.validateResourceIdForNewReservation(EXAMPLE_RESOURCE_ID);
    }

    @Test
    public void noExceptionWhenAllReservationsAreClosedForResourceId() {
        reservation.setStatus(ReservationStatus.CLOSED);
        reservations.add(reservation);
        validator.validateResourceIdForNewReservation(EXAMPLE_RESOURCE_ID);
    }

    @Test
    public void shouldThrowExceptionIfThereAreClosedReservationForProlongation(){
        reservation.setStatus(ReservationStatus.CLOSED);
        when(reservationDAO.getByID(any(Long.class))).thenReturn(Optional.of(reservation));
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Closed Reservation can not be prolonged. " +
                "Try to make a new reservation for this resource");
        validator.validateReservationStatusForProlongation(12345l);
    }

    @Test
    public void shouldThrowExceptionWhenReservationStartDateIsBeforeToday() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservation Start Date must be set for today!");
        validator.validateStartDateForReservation(START_DATE_YESTERDAY);
    }

    @Test
    public void shouldThrowExceptionWhenReservationStartDateIsBeforeYesterday() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservation Start Date must be set for today!");
        validator.validateStartDateForReservation(START_DATE_TOMORROW);
    }

    @Test
    public void noExceptionWhenReservationStartDateIsToday() {
        validator.validateStartDateForReservation(START_DATE_TODAY);
    }

    @Test
    public void shouldThrowExceptionWhenReservationEndDateIsTooEarly() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservation End Date must be set for no less " +
                "than 7 and no more than 30 days from now!");
        validator.validateEndDateForReservation(END_DATE_TOO_EARLY);
    }

    @Test
    public void shouldThrowExceptionWhenReservationEndDateIsTooLate() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservation End Date must be set for no less " +
                "than 7 and no more than 30 days from now!");
        validator.validateEndDateForReservation(END_DATE_TOO_LATE);
    }

    @Test
    public void noExceptionWhenReservationEndDateIsMinPossible() {
        validator.validateEndDateForReservation(END_DATE_MIN_POSSIBLE);
    }

    @Test
    public void noExceptionWhenReservationEndDateIsMaxPossible() {
        validator.validateEndDateForReservation(END_DATE_MAX_POSSIBLE);
    }
}
