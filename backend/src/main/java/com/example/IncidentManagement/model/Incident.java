package com.example.IncidentManagement.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key with auto-incremented value

    @Column(nullable = false, unique = true)
    private String incidentId; // Custom unique field for incident identifier

    @Column(nullable = false)
    private String reporterName; // Name of the person reporting the incident

    @Column(nullable = false, length = 2000)
    private String details; // Description of the incident

    @Column(nullable = false)
    private LocalDateTime reportedAt; // Date and time the incident was reported

    @Column(nullable = false)
    private String priority; // Incident priority (e.g., Low, Medium, High)

    @Column(nullable = false)
    private String status; // Current status of the incident (e.g., Open, Closed)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // Foreign key to User table
    private User user; // Many incidents can be associated with one user (reporter)

    // Default constructor
    public Incident() {}

    // Constructor with fields
    public Incident(String incidentId, String reporterName, String details, LocalDateTime reportedAt, String priority, String status, User user) {
        this.incidentId = incidentId;
        this.reporterName = reporterName;
        this.details = details;
        this.reportedAt = reportedAt;
        this.priority = priority;
        this.status = status;
        this.user = user;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getReportedAt() {
        return reportedAt;
    }

    public void setReportedAt(LocalDateTime reportedAt) {
        this.reportedAt = reportedAt;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
