package lv.javaguru.java2.services;

public class UserValidatorImpl implements UserValidator {

    //// TODO: 01/04/17 update methods after User class will be refactored

    @Override
    public void validate(String firstName, String lastName) {
        validateFirstName(firstName);
        validateLastName(lastName);
    }

    private void validateFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First Name must be not empty!");
        }
    }

    private void validateLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last Name must be not empty!");
        }
    }

}
