package lv.javaguru.java2.domain;

/**
 * Created by user Jekaterina Saveljeva on 22.03.2017.
 */
public class UserAccount {
    private Long userAccountId;
    private String firstName;
    private String lastName;
    private UserAccountState state;


    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserAccountState getState() {
        return state;
    }

    public void setState(UserAccountState state) {
        this.state = state;
    }
}
