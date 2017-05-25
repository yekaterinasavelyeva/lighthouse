package lv.javaguru.java2.services.validators.impls;

import lv.javaguru.java2.domain.ResourceType;
import lv.javaguru.java2.domain.UserAccountState;
import lv.javaguru.java2.services.validators.DataInputValidator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by mobileqa on 28/04/17.
 */
@Component
class DataInputValidatorImpl implements DataInputValidator {

    @Override
    public void validateStartDateInput(LocalDate dateFrom) {
        if (dateFrom == null) {
            throw new IllegalArgumentException("Start date cannot be empty.");
        }
    }

    @Override
    public void validateEndDateInput(LocalDate dateTo) {
        if (dateTo == null) {
            throw new IllegalArgumentException("End date cannot be empty.");
        }
    }

    @Override
    public void validateReservationIdInput(Long reservationId) {
        if (reservationId == null || reservationId < 0) {
            throw new IllegalArgumentException("Reservation ID cannot be null or negative");
        }
    }

    @Override
    public void validateResourceIdInput(Long resourceId) {
        if (resourceId == null || resourceId < 0) {
            throw new IllegalArgumentException("Resource ID cannot be null or negative");
        }
    }

    @Override
    public void validateResourceTypeInput(ResourceType resourceType) {
        if (resourceType == null) {
            throw new IllegalArgumentException("Resource Type cannot be empty!");
        }
    }

    @Override
    public void validateResourceTitleInput(String resourceTitle) {
        if (resourceTitle == null || resourceTitle.isEmpty()) {
            throw new IllegalArgumentException("Resource title cannot be empty!");
        }
    }

    @Override
    public void validateResourceAuthorInput(String resourceAuthor) {
        if (resourceAuthor == null || resourceAuthor.isEmpty()) {
            throw new IllegalArgumentException("Resource Author cannot be empty!");
        }
    }

    @Override
    public void validateUserAccountIdInput(Long userAccountId) {
        if (userAccountId == null || userAccountId<0) {
            throw new IllegalArgumentException("Account ID cannot be null or negative");
        }
    }

    @Override
    public void validateUserAccountFirstNameInput(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First Name cannot be empty!");
        }
    }

    @Override
    public void validateUserAccountLastNameInput(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last Name cannot be empty!");
        }
    }

    @Override
    public void validateUserAccountStateInput(UserAccountState state) {
        if (state == null) {
            throw new IllegalArgumentException("Account type cannot be empty!");
        }
    }
}
