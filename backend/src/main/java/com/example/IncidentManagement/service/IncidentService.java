package com.example.IncidentManagement.service;

import com.example.IncidentManagement.model.Incident;
import com.example.IncidentManagement.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    // Create Incident
    public Incident createIncident(Incident incident) {
        incident.setIncidentId(generateIncidentId()); // Generate a unique incident ID
        return incidentRepository.save(incident); // Save to the database
    }

    // Get Incident by ID
    public Incident getIncident(String incidentId) {
        return incidentRepository.findByIncidentId(incidentId); // Fetch incident by ID
    }

    // Method to generate a unique incident ID (e.g., RMG12345 or similar)
    private String generateIncidentId() {
        return "RMG" + (int)(Math.random() * 100000) + System.currentTimeMillis(); // Combining random number and timestamp for uniqueness
    }
}
