package lv.javaguru.java2.services.useraccount.validate;

/**
 * Created by user on 15.04.2017.
 */
public class UserAccountIdValidatorImpl implements UserAccountIdValidator {

    public void validate(Long accountID){
        validateAccountID(accountID);
    }

    private  void validateAccountID(Long accountId){
        if (accountId == null || accountId<0) {
            throw new IllegalArgumentException("Account ID must not be empty or less than 0");
        }
    }
}
