import React from 'react';
import './HomePage.css'; // Import custom styles

function HomePage() {
  return (
    <div className="homepage-container">
      <div className="homepage-content">
        <h1 className="homepage-title">Welcome to the Incident Management System!</h1>
        <p className="homepage-description">
          This is your dashboard for managing incidents, users, and more. Use the navigation bar to access different pages.
        </p>
        <a href="/incident" className="cta-button">Manage Incidents</a>
      </div>
    </div>
  );
}

export default HomePage;
