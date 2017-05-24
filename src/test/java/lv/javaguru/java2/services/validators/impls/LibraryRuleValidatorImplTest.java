package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.services.validators.LibraryRuleValidator;
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
 * Created by Viktor on 2017.05.22..
 */
@RunWith(MockitoJUnitRunner.class)
public class LibraryRuleValidatorImplTest {
    private static final Long EXAMPLE_ID = 1234L;
    private static final int RELEASE_YEAR_BELOW_LIMIT = 0;
    private static final int RELEASE_YEAR_OVER_LIMIT = LocalDate.now().getYear() + 1;
    private static final int RELEASE_YEAR_MIN_POSSIBLE = 1;
    private static final int RELEASE_YEAR_MAX_POSSIBLE = LocalDate.now().getYear();
    private static final LocalDate START_DATE_ONLY_POSSIBLE = LocalDate.now();
    private static final LocalDate START_DATE_TOO_EARLY = LocalDate.now().minusDays(1);
    private static final LocalDate START_DATE_TOO_LATE = LocalDate.now().plusDays(1);
    private static final LocalDate END_DATE_TOO_EARLY = LocalDate.now().plusDays(6);
    private static final LocalDate END_DATE_MIN_POSSIBLE = LocalDate.now().plusDays(7);
    private static final LocalDate END_DATE_TOO_LATE = LocalDate.now().plusDays(31);
    private static final LocalDate END_DATE_MAX_POSSIBLE = LocalDate.now().plusDays(30);

    private List<Reservation> reservations;
    private Reservation reservation;

    @Mock
    ReservationDAO reservationDAO;
    @InjectMocks
    private LibraryRuleValidator validator = new LibraryRuleValidatorImpl();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void init() {
        reservations = new ArrayList<>();
        reservation = new Reservation();
        when(reservationDAO.getByResourceID(EXAMPLE_ID))
                .thenReturn(reservations);
        when(reservationDAO.getByID(EXAMPLE_ID))
                .thenReturn(Optional.of(reservation));

    }

    @Test
    public void shouldThrowExceptionIfReleaseYearBelowLimit() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Release Year cannot be 0 or greater than current year!");
        validator.validateResourceReleaseYearLimits(RELEASE_YEAR_BELOW_LIMIT);
    }

    @Test
    public void shouldThrowExceptionIfReleaseYearOverLimit() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Release Year cannot be 0 or greater than current year!");
        validator.validateResourceReleaseYearLimits(RELEASE_YEAR_OVER_LIMIT);
    }

    @Test
    public void noExceptionWhenReleaseYearMinPossible() {
        validator.validateResourceReleaseYearLimits(RELEASE_YEAR_MIN_POSSIBLE);
    }

    @Test
    public void noExceptionWhenReleaseYearMaxPossible() {
        validator.validateResourceReleaseYearLimits(RELEASE_YEAR_MAX_POSSIBLE);
    }

    @Test
    public void shouldThrowExceptionIfThereIsOpenReservationForResource() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Cannot delete resource. There is open reservation for it");
        reservation.setStatus(ReservationStatus.OPEN);
        reservations.add(reservation);
        validator.validateResourceReservationStatusWhenDeletingResource(EXAMPLE_ID);
    }

    @Test
    public void noExceptionIfAllReservationsForResourceClosed() {
        reservation.setStatus(ReservationStatus.CLOSED);
        reservations.add(reservation);
        validator.validateResourceReservationStatusWhenDeletingResource(EXAMPLE_ID);
    }

    @Test
    public void noExceptionIfThereAreNoReservationsForResource() {
        validator.validateResourceReservationStatusWhenDeletingResource(EXAMPLE_ID);
    }

    @Test
    public void shouldThrowExceptionIfThereAreOpenedReservationsForResourceId() {
        reservation.setStatus(ReservationStatus.OPEN);
        reservations.add(reservation);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("There are opened reservations for this resource. Cannot create new reservation.");
        validator.validateResourceReservationStatusWhenCreatingReservation(EXAMPLE_ID);
    }

    @Test
    public void noExceptionWhenAllReservationsAreClosedForResourceId() {
        reservation.setStatus(ReservationStatus.CLOSED);
        reservations.add(reservation);
        validator.validateResourceReservationStatusWhenCreatingReservation(EXAMPLE_ID);
    }

    @Test
    public void shouldThrowExceptionIfThereAreClosedReservationForProlongation(){
        reservation.setStatus(ReservationStatus.CLOSED);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Closed Reservation can not be prolonged. " +
                "Try to make a new reservation for this resource");
        validator.validateReservationStatusWhenProlongingIt(EXAMPLE_ID);
    }

    @Test
    public void shouldThrowExceptionWhenReservationStartDateIsBeforeToday() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservation Start Date must be set for today!");
        validator.validateReservationStartDateLimits(START_DATE_TOO_EARLY);
    }

    @Test
    public void shouldThrowExceptionWhenReservationStartDateIsBeforeYesterday() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservation Start Date must be set for today!");
        validator.validateReservationStartDateLimits(START_DATE_TOO_LATE);
    }

    @Test
    public void noExceptionWhenReservationStartDateIsToday() {
        validator.validateReservationStartDateLimits(START_DATE_ONLY_POSSIBLE);
    }

    @Test
    public void shouldThrowExceptionWhenReservationEndDateIsTooEarly() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservation End Date must be set for no less " +
                "than 7 and no more than 30 days from now!");
        validator.validateReservationEndDateLimits(END_DATE_TOO_EARLY);
    }

    @Test
    public void shouldThrowExceptionWhenReservationEndDateIsTooLate() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Reservation End Date must be set for no less " +
                "than 7 and no more than 30 days from now!");
        validator.validateReservationEndDateLimits(END_DATE_TOO_LATE);
    }

    @Test
    public void noExceptionWhenReservationEndDateIsMinPossible() {
        validator.validateReservationEndDateLimits(END_DATE_MIN_POSSIBLE);
    }

    @Test
    public void noExceptionWhenReservationEndDateIsMaxPossible() {
        validator.validateReservationEndDateLimits(END_DATE_MAX_POSSIBLE);
    }
}