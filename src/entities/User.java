package entities;

import java.io.Serializable;

public class User implements Serializable {
    private Integer userID;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String password;
    private String country;
    private String organization;


    public User() {
        this.userID = -1;
        this.fullName = "DefaultFullName";
        this.phoneNumber = "XX-XXX-XXX-XX-XX-XXX";
        this.email = "DefaultEmail@default.com";
        this.password = "DefaultPass123$";
        this.country = "DefaultCountryName";
        this.organization = "DefaultOrganizationName";
    }

    public User(Integer userID, String fullName, String phoneNumber, String email, String password, String country) {
        this.userID = userID;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.country = country;
        this.organization = "None";
    }

    public User(Integer userID, String fullName, String phoneNumber, String email, String password, String country,
                String organization) {
        this.userID = userID;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.country = country;
        this.organization = organization;
    }


    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return ("ID: " + userID + "\nFull Name: " + fullName + "\nEmail: " + email + "\nOrganization: " + organization
            + "\nPhone number: " + phoneNumber + "\nPassword: " + password + "\nCountry: " + country);
    }
}
