package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.services.validators.LibraryRuleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by Viktor on 2017.05.22..
 */

@Component
class LibraryRuleValidatorImpl implements LibraryRuleValidator {

    private static final int MIN_RELEASE_YEAR = 1;
    private static final int MAX_RELEASE_YEAR = LocalDate.now().getYear();
    private static final LocalDate MIN_START_DATE = LocalDate.now();
    private static final LocalDate MAX_START_DATE = LocalDate.now();
    private static final LocalDate MIN_END_DATE = LocalDate.now().plusDays(7);
    private static final LocalDate MAX_END_DATE = LocalDate.now().plusDays(30);

    @Autowired
    @Qualifier("HibernateReservationDAO")
    private ReservationDAO reservationDAO;

    @Override
    public void validateResourceReleaseYearLimits(int releaseYear) {
        if (releaseYear < MIN_RELEASE_YEAR || releaseYear > MAX_RELEASE_YEAR) {
            throw new IllegalArgumentException("Release Year cannot be 0 or greater than current year!");
        }
    }

    @Override
    public void validateResourceReservationStatusWhenDeletingResource(Long resourceId) {
        if (isAnyOpenedReservationForResource(resourceId)) {
            throw new IllegalArgumentException("Cannot delete resource. There is open reservation for it");
        }
    }

    @Override
    public void validateResourceReservationStatusWhenCreatingReservation(Long resourceId) {
        if (isAnyOpenedReservationForResource(resourceId)) {
            throw new IllegalArgumentException("There are opened reservations for this resource. " +
                    "Cannot create new reservation.");
        }
    }

    @Override
    public void validateReservationStatusWhenProlongingIt(Long reservationId){
        if (isReservationClosed(reservationId)) {
            throw new IllegalArgumentException("Closed Reservation can not be prolonged. " +
                    "Try to make a new reservation for this resource");
        }
    }

    @Override
    public void validateReservationStartDateLimits(LocalDate dateFrom) {
        if (isLessThanMinLimit(dateFrom, MIN_START_DATE)
                || isMoreThanMaxLimit(dateFrom, MAX_START_DATE)) {
            throw new IllegalArgumentException("Reservation Start Date must be set for today!");
        }
    }
    @Override
    public void validateReservationEndDateLimits(LocalDate dateTo){
        if (isLessThanMinLimit(dateTo, MIN_END_DATE)
                || isMoreThanMaxLimit(dateTo, MAX_END_DATE)) {
            throw new IllegalArgumentException("Reservation End Date must be set for no less " +
                    "than 7 and no more than 30 days from now!");
        }
    }

    private boolean isLessThanMinLimit(LocalDate date, LocalDate minLimit) {
        return date.isBefore(minLimit);
    }

    private boolean isMoreThanMaxLimit(LocalDate date, LocalDate maxLimit) {
        return date.isAfter(maxLimit);
    }

    private boolean isAnyOpenedReservationForResource(Long resourceId) {
        return reservationDAO.getByResourceID(resourceId)
                .stream()
                .anyMatch(reservation
                        -> reservation
                        .getStatus()
                        .equals(ReservationStatus.OPEN));
    }

    private boolean isReservationClosed(Long reservationId){
        return reservationDAO.getByID(reservationId).get()
                .getStatus().equals(ReservationStatus.CLOSED);

    }
}
