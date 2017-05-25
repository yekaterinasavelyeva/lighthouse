package lv.javaguru.java2.services.validators;

import lv.javaguru.java2.domain.ResourceType;
import lv.javaguru.java2.domain.UserAccountState;

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

    void validateUserAccountIdInput(Long userAccountId);
    void validateUserAccountFirstNameInput(String firstName);
    void validateUserAccountLastNameInput(String lastName);
    void validateUserAccountStateInput(UserAccountState state);
}
