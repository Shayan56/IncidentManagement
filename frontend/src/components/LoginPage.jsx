import React, { useState } from 'react';
import axios from 'axios';
import './LoginPage.css'; // Importing the CSS for styling

function LoginPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = (e) => {
    e.preventDefault();
    const user = { email, password };

    axios.post('http://localhost:8080/users/login', user)
      .then(response => {
        console.log('Login successful:', response);
        localStorage.setItem('token', response.data.token); // store JWT token
        alert('Login successful!');
      })
      .catch(error => {
        console.error('Login error:', error);
        alert('Login failed');
      });
  };

  return (
    <div className="login-container">
      <div className="login-form">
        <h2 className="login-title">Login</h2>
        <form onSubmit={handleLogin}>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            placeholder="Email"
            required
            className="login-input"
          />
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Password"
            required
            className="login-input"
          />
          <button type="submit" className="login-button">Login</button>
        </form>
      </div>
    </div>
  );
}

export default LoginPage;
