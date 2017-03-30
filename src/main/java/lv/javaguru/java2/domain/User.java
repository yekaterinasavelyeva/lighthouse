package lv.javaguru.java2.domain;

public class User {

    //// TODO: 30/03/17 delete unnecessary properties and add Login/Password

    private Long userId;
    private String firstName;
    private String lastName;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
}
