import React, { useState, useEffect } from 'react';
import './Styles.css';



const UpdateBuyer = () => {
  const [buyerId, setBuyerId] = useState('');
  const [buyer, setBuyer] = useState({
    email: '',
    firstName: '',
    lastName: '',
    password: '',
    loyaltyPoints: 0,
  });

  useEffect(() => {
    
    if (buyerId) {
      const fetchBuyerDetails = async () => {
        try {
          const response = await fetch(`http://localhost:8080/api/buyers/edit/${buyerId}`);
          if (response.ok) {
            const result = await response.json();
            setBuyer(result);
          } else {
            console.error('Failed to fetch buyer details');
          }
        } catch (error) {
          console.error('Error fetching buyer details:', error);
        }
      };

      fetchBuyerDetails();
    }
  }, [buyerId]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch(`http://localhost:8080/api/buyers/edit/${buyerId}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(buyer),
      });

      if (response.ok) {
        console.log('Buyer updated successfully');
      } else {
        console.error('Failed to update buyer');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  const handleChangeBuyerId = (e) => {
    setBuyerId(e.target.value);
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setBuyer({ ...buyer, [name]: name === 'loyaltyPoints' ? parseInt(value, 10) : value });
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="buyerId">Select Buyer ID:</label>
          <input
            id="buyerId"
            name="buyerId"
            type="number"
            value={buyerId}
            onChange={handleChangeBuyerId}
            required
          />
        </div>
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
          <label htmlFor="loyaltyPoints">Loyalty Points:</label>
          <input
            id="loyaltyPoints"
            name="loyaltyPoints"
            type="text"
            value={buyer.loyaltyPoints}
            onChange={handleChange}
          />
        </div>
  
        <div>
          <input className="button-link" type="submit" value="Update Buyer" />
        </div>
      </form>
  
      <p>
        <a className="button-link" href="/api/buyers/list">
          Back to List
        </a>
      </p>
    </div>
  );
  };  
  export default UpdateBuyer;