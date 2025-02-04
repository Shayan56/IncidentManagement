package com.example.IncidentManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "app_user")
@Data  // Lombok will generate getters, setters, equals, hashcode, and toString methods
@NoArgsConstructor  // Lombok generates the default no-args constructor
@AllArgsConstructor  // Lombok generates the constructor with all fields
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column
    private String phoneNumber;

    @Column
    private String address;

    @Column
    private String pinCode;

    @Column
    private String city;

    @Column
    private String country;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Incident> incidents;
}
