// src/App.js
import React from 'react';
import { Routes, Route } from 'react-router-dom';

// Import components
import LoginPage from './components/LoginPage';
import RegistrationPage from './components/RegistrationPage';
import ForgotPasswordPage from './components/ForgotPasswordPage';
import IncidentPage from './components/IncidentPage';
import Navbar from './components/Navbar';
import HomePage from './components/HomePage';  // Import the HomePage component

// Import axiosConfig to enable global axios configuration
import './axiosConfig';

function App() {
  return (
    <div>
      {/* Navbar component, will appear on all pages */}
      <Navbar />

      {/* Define Routes */}
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegistrationPage />} />
        <Route path="/forgot-password" element={<ForgotPasswordPage />} />
        <Route path="/incident" element={<IncidentPage />} />
      </Routes>
    </div>
  );
}

export default App;
