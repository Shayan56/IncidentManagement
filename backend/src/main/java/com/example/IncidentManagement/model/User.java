package com.example.IncidentManagement.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the user

    @Column(nullable = false, unique = true) // Ensures email is unique and not null
    private String email;

    @Column(nullable = false, unique = true) // Ensures username is unique and not null
    private String userName;

    @Column // Optional phone number
    private String phoneNumber;

    @Column // Optional address
    private String address;

    @Column // Optional pin code
    private String pinCode;

    @Column // Optional city
    private String city;

    @Column // Optional country
    private String country;

    @Column(nullable = false) // Ensures password is not null
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // One-to-many relationship with Incident
    private List<Incident> incidents;

    // Default constructor
    public User() {}

    // Constructor with fields (optional, can be used for easier instantiation)
    public User(String email, String userName, String phoneNumber, String address, 
                String pinCode, String city, String country, String password) {
        this.email = email;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.pinCode = pinCode;
        this.city = city;
        this.country = country;
        this.password = password;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(List<Incident> incidents) {
        this.incidents = incidents;
    }
}
