package com.example.IncidentManagement.controller;

import com.example.IncidentManagement.model.Incident;
import com.example.IncidentManagement.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/incidents") // Base path for incident-related APIs
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    /**
     * Endpoint to create a new incident.
     * 
     * @param incident The incident details to be created.
     * @return The created incident with a status code of 201 (Created).
     */
    @PostMapping("/create")
    public ResponseEntity<Incident> createIncident(@RequestBody Incident incident) {
        try {
            Incident createdIncident = incidentService.createIncident(incident);
            return new ResponseEntity<>(createdIncident, HttpStatus.CREATED); // Returns status 201
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Returns status 500 for server error
        }
    }

    /**
     * Endpoint to retrieve an incident by its unique ID.
     * 
     * @param incidentId The unique ID of the incident.
     * @return The incident details if found, or a 404 status if not found.
     */
    @GetMapping("/{incidentId}")
    public ResponseEntity<Incident> getIncident(@PathVariable String incidentId) {
        Incident incident = incidentService.getIncident(incidentId);
        if (incident == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 if the incident is not found
        }
        return new ResponseEntity<>(incident, HttpStatus.OK); // 200 OK with the incident data
    }
}
