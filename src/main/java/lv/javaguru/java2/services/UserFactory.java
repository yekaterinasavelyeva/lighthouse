package lv.javaguru.java2.services;

import lv.javaguru.java2.domain.User;

public interface UserFactory {

    User create(String firstName, String lastName);

}
