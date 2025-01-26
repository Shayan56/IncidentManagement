// src/components/IncidentPage.jsx
import React, { useState } from 'react';
import axios from 'axios';
import './IncidentPage.css'; // Import the styling

function IncidentPage() {
    const [incidentDetails, setIncidentDetails] = useState('');
    const [priority, setPriority] = useState('Medium');
    const [status, setStatus] = useState('Open');
    const [incidentID, setIncidentID] = useState('');
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');

    const handleCreateIncident = (e) => {
        e.preventDefault();

        const incident = {
            incidentDetails,
            priority,
            status,
            incidentID: `RMG${Math.floor(Math.random() * 100000)}2022`,
        };

        setLoading(true);
        setError('');
        setMessage('');

        axios.post('http://localhost:8080/incidents/create', incident)
            .then(response => {
                console.log('Incident created:', response);
                setMessage('Incident created successfully!');
                setLoading(false);
                setIncidentDetails('');
            })
            .catch(error => {
                console.error('Error creating incident:', error);
                setError('Failed to create incident. Please try again.');
                setLoading(false);
            });
    };

    return (
        <div className="incident-container">
            <div className="incident-form">
                <h2 className="incident-title">Create Incident</h2>
                <form onSubmit={handleCreateIncident}>
                    <div className="form-group">
                        <textarea
                            value={incidentDetails}
                            onChange={(e) => setIncidentDetails(e.target.value)}
                            placeholder="Describe the incident"
                            className="incident-input"
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label>Priority:</label>
                        <select
                            value={priority}
                            onChange={(e) => setPriority(e.target.value)}
                            className="incident-input"
                        >
                            <option value="Low">Low</option>
                            <option value="Medium">Medium</option>
                            <option value="High">High</option>
                        </select>
                    </div>
                    <div className="form-group">
                        <label>Status:</label>
                        <select
                            value={status}
                            onChange={(e) => setStatus(e.target.value)}
                            className="incident-input"
                        >
                            <option value="Open">Open</option>
                            <option value="In Progress">In Progress</option>
                            <option value="Closed">Closed</option>
                        </select>
                    </div>
                    {error && <p className="error-message">{error}</p>}
                    {message && <p className="success-message">{message}</p>}
                    <div className="form-group">
                        <button type="submit" className="submit-button" disabled={loading}>
                            {loading ? 'Creating Incident...' : 'Create Incident'}
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default IncidentPage;
