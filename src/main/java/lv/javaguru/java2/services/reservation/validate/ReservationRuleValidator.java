package lv.javaguru.java2.services.reservation.validate;

/**
 * Created by vbarbasins on 2017.04.28..
 */
public interface ReservationRuleValidator {

    void validateReservationForResourceMustBeClosed(Long resourceId);
}
