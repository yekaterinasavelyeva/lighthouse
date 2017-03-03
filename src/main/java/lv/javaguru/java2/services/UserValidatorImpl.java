package lv.javaguru.java2.services;

public class UserValidatorImpl implements UserValidator {

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
