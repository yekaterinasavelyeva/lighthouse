package lv.javaguru.java2.domain;

/**
 * Created by user on 22.03.2017.
 */
public class UserAccountBuilder {

    private String firstName;
    private String lastName;
    private UserAccountState state;


    private UserAccountBuilder() {}

    public static UserAccountBuilder createUserAccount() {
        return new UserAccountBuilder();
    }

    public UserAccount build() {
        UserAccount account = new UserAccount();
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setState(state);
        return account;
    }

    public UserAccountBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserAccountBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserAccountBuilder withStatus(UserAccountState state) {
        this.state = state;
        return this;
    }

}
