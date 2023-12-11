import React, { useState } from 'react';
import './LoginForm.css';



const LoginForm = ( )=> {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch('http://localhost:8080/api/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `email=${email}&password=${password}`,
      });

      const data = await response.json();

      if (data.success) {
        const buyerId = data.buyerId;
        sessionStorage.setItem('buyerId', buyerId);
       window.location.href='http://localhost:3000/api/products/list';
      } else {
        setError(data.message);
      }
    } catch (error) {
      console.error('Error:', error);
      setError('An error occurred during login.');
    }
  };

  

  return (
    <div className="login-container">
      <h1>Welcome to My Marketplace</h1>
      <h2>Login</h2>
      <form className="login-form" onSubmit={handleLogin}>
        <div className="form-group">
          <label>Email:</label>
          <input
            type="text"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div className="form-group">
          <label>Password:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <button type="submit">Login</button>
        <div>
        <a className="button-link" href="/api/buyers/add">
          Sign Up
        </a>
        </div>
      </form>
    </div>
  );
}

export default LoginForm;
