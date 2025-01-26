import React, { useState } from 'react';
import axios from 'axios';
import './ForgotPasswordPage.css'; // Import the styling

function ForgotPasswordPage() {
  const [email, setEmail] = useState('');
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  const handleResetPassword = (e) => {
    e.preventDefault();
    
    // Basic validation
    if (!email) {
      setError('Email is required');
      return;
    }

    setLoading(true);
    setError('');
    setMessage('');

    axios.post('http://localhost:8080/users/forgot-password', { email })
      .then((response) => {
        console.log('Password reset request sent:', response);
        setMessage('Password reset link sent to your email!');
        setLoading(false);
      })
      .catch((error) => {
        console.error('Error resetting password:', error);
        setError('Failed to send reset link. Please try again.');
        setLoading(false);
      });
  };

  return (
    <div className="forgot-password-container">
      <div className="forgot-password-form">
        <h2 className="forgot-password-title">Forgot Password</h2>
        <form onSubmit={handleResetPassword}>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            placeholder="Enter your email"
            className="forgot-password-input"
            required
          />
          {error && <p className="error-message">{error}</p>}
          {message && <p className="success-message">{message}</p>}
          <button type="submit" className="reset-password-button" disabled={loading}>
            {loading ? 'Sending...' : 'Reset Password'}
          </button>
        </form>
      </div>
    </div>
  );
}

export default ForgotPasswordPage;
