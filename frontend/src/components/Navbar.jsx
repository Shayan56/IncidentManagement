// src/components/Navbar.jsx
import React from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css';  // Import custom styling for the navbar

function Navbar() {
  return (
    <nav className="navbar">
      <div className="navbar-logo">
        <h2>Incident Management</h2>
      </div>
      <ul className="navbar-links">
        <li><Link to="/" className="navbar-item">Home</Link></li>
        <li><Link to="/login" className="navbar-item">Login</Link></li>
        <li><Link to="/register" className="navbar-item">Register</Link></li>
        <li><Link to="/incident" className="navbar-item">Create Incident</Link></li>
        <li><Link to="/forgot-password" className="navbar-item">Forgot Password</Link></li>
      </ul>
    </nav>
  );
}

export default Navbar;
