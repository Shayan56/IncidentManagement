// src/axiosConfig.js
import axios from 'axios';

// Add a request interceptor to include the token in every request
axios.interceptors.request.use(
  (config) => {
    // Get the token from localStorage (assuming it is saved there after login)
    const userToken = localStorage.getItem('token');
    if (userToken) {
      // Add Authorization header if the token exists
      config.headers.Authorization = `Bearer ${userToken}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Optionally set a base URL for all axios requests (adjust the URL as needed)
axios.defaults.baseURL = 'http://localhost:8080'; // Example backend URL (update as needed)
