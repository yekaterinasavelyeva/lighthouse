package lv.javaguru.java2.services.validators;

import lv.javaguru.java2.domain.ResourceType;

import java.time.LocalDate;

/**
 * Created by mobileqa on 28/04/17.
 */
public interface DataInputValidator {

    void validateStartDateInput(LocalDate dateFrom);
    void validateEndDateInput(LocalDate dateTo);
    void validateReservationIdInput(Long reservationId);
    void validateResourceIdInput(Long resourceId);
    void validateResourceTypeInput(ResourceType resourceType);
    void validateResourceTitleInput(String resourceTitle);
    void validateResourceAuthorInput(String resourceAuthor);
}
