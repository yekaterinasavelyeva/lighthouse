package lv.javaguru.java2.domain;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

/**
 * Created by user Jekaterina Saveljeva on 22.03.2017.
 */

@Entity
@Table(name = "accounts")
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="AccountID", columnDefinition = "serial")
    private Long accountId;

    @Column(name="FirstName", columnDefinition = "bpchar")
    private String firstName;

    @Column(name="LastName", columnDefinition = "bpchar")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name="Status", columnDefinition = "status")
    @Type( type = "pgsql_enum" )
    private UserAccountState state;


    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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
