import React, { useState } from 'react';
import './Styles.css';

const AddBuyer = () => {
  const [buyer, setBuyer] = useState({
    email: '',
    firstName: '',
    lastName: '',
    password: '',
  });

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch(`http://localhost:8080/api/buyers/add`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(buyer),
      });

      if (response.ok) {
        console.log('Buyer added successfully');

      } else {
        console.error('Failed to add buyer');

      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setBuyer({ ...buyer, [name]: value });
  };

  return (
    <div>
      <h1>SignUp Form</h1>

      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="email">Email:</label>
          <input
            id="email"
            name="email"
            type="text"
            value={buyer.email}
            onChange={handleChange}
          />
        </div>
        <div>
          <label htmlFor="firstName">First Name:</label>
          <input
            id="firstName"
            name="firstName"
            type="text"
            value={buyer.firstName}
            onChange={handleChange}
          />
        </div>
        <div>
          <label htmlFor="lastName">Last Name:</label>
          <input
            id="lastName"
            name="lastName"
            type="text"
            value={buyer.lastName}
            onChange={handleChange}
          />
        </div>
        <div>
          <label htmlFor="password">Password:</label>
          <input
            id="password"
            name="password"
            type="text"
            value={buyer.password}
            onChange={handleChange}
          />
        </div>
        <div>
          <input className="button-link" type="submit" value="Sign up" />
        </div>
      </form>

      
    </div>
  );
};

export default AddBuyer;
