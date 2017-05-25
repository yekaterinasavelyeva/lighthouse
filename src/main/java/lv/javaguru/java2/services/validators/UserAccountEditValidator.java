package lv.javaguru.java2.services.validators;

import lv.javaguru.java2.domain.UserAccountState;

/**
 * Created by Viktor on 2017.05.25..
 */
public interface UserAccountEditValidator {
    void validate(Long userAccountId, String firstName,
                  String lastName, UserAccountState state);
}
