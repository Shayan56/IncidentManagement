package com.example.IncidentManagement.repository;

import com.example.IncidentManagement.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {

    /**
     * Method to find an incident by its unique Incident ID.
     *
     * @param incidentId The unique identifier for the incident.
     * @return The Incident object if found, otherwise null.
     */
    Incident findByIncidentId(String incidentId);
}
