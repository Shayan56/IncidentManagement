import React from 'react';
import ReactDOM from 'react-dom/client';  // Import from 'react-dom/client'
import { BrowserRouter } from 'react-router-dom';  // Import BrowserRouter
import App from './App';
import './App.css';

// Create a root element using createRoot()
const root = ReactDOM.createRoot(document.getElementById('root'));

// Render the App component wrapped inside BrowserRouter
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </React.StrictMode>
);
