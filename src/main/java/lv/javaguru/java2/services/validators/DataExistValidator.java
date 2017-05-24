package lv.javaguru.java2.services.validators;

/**
 * Created by mobileqa on 27/04/17.
 */
public interface DataExistValidator {
    void validateReservationExistForResourceId(Long resourceId);

    void validateReservationExistForUserAccountId(Long userAccountId);

    void validateReservationIdExist(Long reservationId);

    void validateResourceIdExists(Long resourceId);
}
