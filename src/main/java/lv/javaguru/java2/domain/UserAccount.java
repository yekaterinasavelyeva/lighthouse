package lv.javaguru.java2.domain;

import javax.persistence.*;

/**
 * Created by user Jekaterina Saveljeva on 22.03.2017.
 */

@Entity
@Table(name = "accounts")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="AccountID", columnDefinition = "int(11)")
    private Long userAccountId;

    @Column(name="FirstName", columnDefinition = "char(32)")
    private String firstName;

    @Column(name="LastName", columnDefinition = "char(32)")
    private String lastName;

    @Column(name="Status", columnDefinition = "enum('ADMIN','VISITOR')")
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
