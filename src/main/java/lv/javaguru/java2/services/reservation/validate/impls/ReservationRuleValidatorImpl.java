package lv.javaguru.java2.services.reservation.validate.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.services.reservation.validate.ReservationRuleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by vbarbasins on 2017.04.28..
 */
@Component
public class ReservationRuleValidatorImpl implements ReservationRuleValidator {
    @Autowired
    ReservationDAO reservationDAO;

    private static final LocalDate MIN_START_DATE = LocalDate.now();
    private static final LocalDate MAX_START_DATE = LocalDate.now();
    private static final LocalDate MIN_END_DATE = LocalDate.now().plusDays(7);
    private static final LocalDate MAX_END_DATE = LocalDate.now().plusDays(30);

    @Override
    public void validateResourceIdForNewReservation(Long resourceId) {
        boolean isAnyOpenedReservation = ifDatabaseHasOpenedReservationsForResource(resourceId);
        if (isAnyOpenedReservation) {
            throw new IllegalArgumentException("There are opened reservations for this resource. Cannot create new reservation.");
        }
    }

    @Override
    public void validateStartDateForNewReservation(LocalDate dateFrom) {
        if (isLessThanMinLimit(dateFrom, MIN_START_DATE)
                || isMoreThanMaxLimit(dateFrom, MAX_START_DATE)) {
            throw new IllegalArgumentException("Reservation Start Date must be set for today!");
        }
    }
    @Override
    public void validateEndDateForNewReservation(LocalDate dateTo){
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

    private boolean ifDatabaseHasOpenedReservationsForResource(Long resourceId) {
        return reservationDAO.getByResourceID(resourceId)
                .stream()
                .anyMatch(reservation
                        -> reservation
                        .getStatus()
                        .equals(ReservationStatus.OPEN));
    }
}
