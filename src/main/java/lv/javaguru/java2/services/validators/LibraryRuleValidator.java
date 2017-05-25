package lv.javaguru.java2.services.validators;

import java.time.LocalDate;

/**
 * Created by Viktor on 2017.05.22..
 */
public interface LibraryRuleValidator {

    void validateResourceReleaseYearLimits(int releaseYear);

    void validateResourceReservationStatusWhenDeletingResource(Long resourceId);

    void validateReservationStatusWhenDeletingUserAccount(Long userAccountId);

    void validateResourceReservationStatusWhenCreatingReservation(Long resourceId);

    void validateReservationStatusWhenProlongingIt(Long reservationId);

    void validateReservationStartDateLimits(LocalDate dateFrom);

    void validateReservationEndDateLimits(LocalDate dateTo);
}
