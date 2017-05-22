package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.ReservationStatus;
import lv.javaguru.java2.services.validators.ResourceRuleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Viktor on 2017.05.22..
 */

@Component
class ResourceRuleValidatorImpl implements ResourceRuleValidator {

    private static final int MIN_RELEASE_YEAR = 1;
    private static final int MAX_RELEASE_YEAR = LocalDate.now().getYear();

    @Autowired
    @Qualifier("HibernateReservationDAO")
    private ReservationDAO reservationDAO;

    @Override
    public void validateReleaseYearForNewResource(int releaseYear) {
        if (releaseYear < MIN_RELEASE_YEAR || releaseYear > MAX_RELEASE_YEAR) {
            throw new IllegalArgumentException("Release Year cannot be 0 or greater than current year!");
        }
    }

    @Override
    public void validateReservationStatusForResource(Long resourceId) {
        if (isAnyOpenedReservationForResource(resourceId)) {
            throw new IllegalArgumentException("Cannot delete resource. There is open reservation for it");
        }
    }

    private boolean isAnyOpenedReservationForResource(Long resourceId) {
        return reservationDAO.getByResourceID(resourceId)
                .stream()
                .anyMatch(reservation
                        -> reservation
                        .getStatus()
                        .equals(ReservationStatus.OPEN));
    }
}
