import React, { useState } from 'react';
import axios from 'axios';
import './RegistrationPage.css'; // Import CSS file

function RegistrationPage() {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const handleRegister = (e) => {
        e.preventDefault();
        const newUser = { name, email, phone, password };

        axios.post('http://localhost:8080/users/register', newUser)
            .then(response => {
                console.log('User registered:', response);
                alert('Registration successful!');
            })
            .catch(error => {
                console.error('Error registering user:', error);
                setErrorMessage('Registration failed. Please try again.');
            });
    };

    return (
        <div className="registration-container">
            <h2 className="registration-heading">Register</h2>
            {errorMessage && <p className="error-message">{errorMessage}</p>}
            <form onSubmit={handleRegister} className="registration-form">
                <input
                    type="text"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    placeholder="Name"
                    required
                    className="registration-input"
                />
                <input
                    type="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    placeholder="Email"
                    required
                    className="registration-input"
                />
                <input
                    type="text"
                    value={phone}
                    onChange={(e) => setPhone(e.target.value)}
                    placeholder="Phone"
                    required
                    className="registration-input"
                />
                <input
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    placeholder="Password"
                    required
                    className="registration-input"
                />
                <button type="submit" className="submit-button">Register</button>
            </form>
        </div>
    );
}

export default RegistrationPage;
