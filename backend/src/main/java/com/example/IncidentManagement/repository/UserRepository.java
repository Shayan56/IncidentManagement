package com.example.IncidentManagement.repository;

import com.example.IncidentManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Method to find a user by their email address.
     *
     * @param email The email of the user.
     * @return The User object if found, otherwise null.
     */
    User findByEmail(String email);
}
