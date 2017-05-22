package lv.javaguru.java2.services.validators;

import java.time.LocalDate;

/**
 * Created by Viktor on 2017.05.22..
 */
public interface ResourceRuleValidator {

    void validateReleaseYearForNewResource(int releaseYear);

    void validateReservationStatusForResource (Long resourceId);
}
